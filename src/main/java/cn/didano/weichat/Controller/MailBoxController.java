package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.didano.weichat.Service.MailBoxService;
import cn.didano.weichat.Service.MailListService;
import cn.didano.weichat.Service.NoticeService;
import cn.didano.weichat.Service.StudentService;
import cn.didano.weichat.config.OssInfo;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.constant.ModulePathType;
import cn.didano.weichat.constant.NoticeModel;
import cn.didano.weichat.constant.NoticeTop;
import cn.didano.weichat.constant.NoticeType;
import cn.didano.weichat.constant.RoleType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_MailBox_Reply;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_UserAndStudent;
import cn.didano.weichat.model.Hand_WholeStudentParents4PhoneBook;
import cn.didano.weichat.model.Hand_addressName;
import cn.didano.weichat.model.Hand_mailRecord;
import cn.didano.weichat.model.Hand_staff4PhoneBook;
import cn.didano.weichat.model.Tb_head_sculpture;
import cn.didano.weichat.model.Tb_mail;
import cn.didano.weichat.model.Tb_mail_reply;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_staffData;
import cn.didano.weichat.model.Tb_student;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.repository.HeadMemoryConfigStorageContainer;
import cn.didano.weichat.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "园长信箱", tags = "园长信箱，提供给表现层")
@RestController
@RequestMapping(value = "/base/mailbox/post/")
public class MailBoxController {
	static Logger logger = Logger.getLogger(MailBoxController.class);
	@Autowired
	private MailBoxService mailBoxService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MailListService mailListService;
	@Autowired
	private StudentService studentService;
	@Autowired
	OssInfo ossInfo;

	/**
	 * 家长页面点击园长信箱
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 家长页面点击园长信箱", notes = " 家长页面点击园长信箱")
	@PostMapping(value = "parentGetMailId/{own_id}/{notice_type}")
	@ResponseBody
	public Out<Hand_mailRecord> parentGetMailId(
			@ApiParam(value = "家长页面点击园长信箱", required = true) @PathVariable("own_id") Integer own_id,
			@PathVariable("notice_type") byte notice_type) {
		logger.info("MailBoxController:parentGetMailId,own_id=" + own_id);

		PageInfo<Tb_notice> notices = null;
		int mailId = 0;
		Hand_mailRecord data = new Hand_mailRecord();
		Out<Hand_mailRecord> back = new Out<Hand_mailRecord>();
		try {

			notices = noticeService.findNoticeByUserId(1, 5, own_id, (byte) RoleType.PARENT, notice_type);
			System.out.println(notice_type);
			if (notices.getList().size() != 0) {
				// 找出邮件的通知
				for (int i = 0; i < notices.getList().size(); i++) {
					if (notice_type == NoticeType.PRINCIPAL_MAIL.getIndex()) {
						mailId = notices.getList().get(i).getSourceId();
						data = findReply_ByNoticeId(mailId).getData();
						// 设置为已读
						noticeService.setNoticeRead(own_id, notices.getList().get(i).getId());
					} else if (notice_type == NoticeType.TeacherChat_Parent.getIndex()) {
						mailId = notices.getList().get(i).getSourceId();
						data = findReply_ByNoticeId(mailId).getData();
						// 设置为已读
						noticeService.setNoticeRead(own_id, notices.getList().get(i).getId());
					}
				}

				back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
			} else {
				back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, ex.getMessage());
		}
		return back;
	}

	/**
	 * 根据园长id,查看园长信箱列表,园长信箱模块
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */

	@PostMapping(value = "mail_findtByBossId/{page}/{size}/{own_id}/{user_type}/{notice_type}")
	@ApiOperation(value = "根据园长id,查看园长信箱列表", notes = "根据园长id,查看园长信箱列表")
	@ResponseBody
	public Out<OutList<Tb_notice>> notice_findtByUserid(@PathVariable("page") int page, @PathVariable("size") int size,
			@PathVariable("own_id") Integer own_id, @PathVariable("user_type") byte user_type,
			@PathVariable("notice_type") byte notice_type) {
		logger.info("访问  NoticeController:notice_findtByUserid,own_id=" + own_id);
		Tb_notice notice = null;
		Tb_head_sculpture head = null;
		List<Tb_notice> mails = null;
		PageInfo<Tb_notice> notices = null;
		OutList<Tb_notice> outList = null;
		Out<OutList<Tb_notice>> back = new Out<OutList<Tb_notice>>();
		try {
			mails = new ArrayList<Tb_notice>();
			notices = noticeService.findNoticeByUserId(page, size, own_id, user_type, notice_type);
			System.out.println(notices.getList().size());
			if (notices.getList().size() != 0) {

				// 转换时间格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = null;
				if (notices.getList().size() != 0) {
					// 获取头像地址

					for (int i = 0; i < notices.getList().size(); i++) {
						notice = notices.getList().get(i);

						if (notice_type == 4) {
							notice.setTitle(notices.getList().get(i).getSenderName().split("的")[0] + "小朋友的家庭");
							head = HeadMemoryConfigStorageContainer.findByOriId(10);
							notice.setHeadUrl(head.getAddress());
							date = sdf.format(notices.getList().get(i).getCreated());
							notice.setDate(date);
							mails.add(notice);
						} else if (notice_type == 5) {

							Tb_noticeUser noticeUser = noticeService.findNoticeUserByNoticeId(notice.getId(),
									(byte) 30);
							// 设置标题
							if (noticeUser != null) {
								Tb_student student = studentService.selectStudentByParentId(noticeUser.getUserId());
								notice.setTitle(student.getName().split("的")[0] + "小朋友的家庭");
							}
							head = HeadMemoryConfigStorageContainer.findByOriId(10);
							notice.setHeadUrl(head.getAddress());
							date = sdf.format(notices.getList().get(i).getCreated());
							notice.setDate(date);
							mails.add(notice);

						}
					}

					// 根据时间排序
					Collections.sort(mails, new Comparator<Tb_notice>() {
						public int compare(Tb_notice o1, Tb_notice o2) {
							return (int) (o2.getCreated().getTime() - o1.getCreated().getTime());
						}
					});

				}
				outList = new OutList<Tb_notice>(mails.size(), mails);
				back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			} else {
				outList = new OutList<Tb_notice>(mails.size(), mails);
				back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage());
		}
		return back;
	}

	/**
	 * 写邮件，回复邮件
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 写邮件，回复邮件", notes = " 写邮件，回复邮件")
	@PostMapping(value = "write_mail")
	@ResponseBody
	public Out<Integer> write_mail(
			@ApiParam(value = "写邮件，回复邮件", required = true) @RequestBody In_MailBox_Reply mail_write) {
		logger.info("MailBoxController:write_mail,mail_write=" + mail_write);

		Hand_UserAndStudent data = null;
		Tb_mail_reply mailReply = null;
		Hand_addressName addressName = null;
		List<Tb_staff> boss = null;
		List<Tb_student_parent> parents = null;
		Out<Integer> back = new Out<Integer>();
		try {
			// 通知id没有时就是写邮件
			if (mail_write.getMailId() == 0) {
				data = new Hand_UserAndStudent();
				data.setStudentId(mail_write.getStudentId());
				data.setUserId(mail_write.getUserId());
				parents = mailBoxService.findParentByStudentId(mail_write.getStudentId());
				// 查询该家长所在学校的园长和行政
				boss = mailBoxService.selectBossByParentId(mail_write.getUserId());
				addressName = mailBoxService.selectAddressName(data);
				// 插入邮件表
				Tb_mail mail = new Tb_mail();
				logger.info("mail_write.getContent()=" + mail_write.getContent());
				// stephen.wang 2017-8-30
				// 内容里面如果有临时图片，执行将临时图片转换为正式图片，上传一开始都是临时图片，当执行保存时，转为正式
				String newContent = FileUtil.transferTempUrl2FormalUrlWithAliOss(mail_write.getContent(), ossInfo);
				mail.setContent(newContent);
				logger.info("newContent=" + newContent);
				mail.setCreated(new Date());
				mail.setSenderId(mail_write.getUserId());
				mail.setSenderName(addressName.getName() + "的" + addressName.getRelation_title());
				mailBoxService.insertMailSelective(mail);
				// 插入通知表
				Tb_notice notice = new Tb_notice();
				notice.setCreated(new Date());
				notice.setNoticeModel(NoticeModel.INSIDE_URL.getIndex());
				notice.setNoticeType(NoticeType.PRINCIPAL_MAIL.getIndex());
				notice.setPriority(NoticeTop.NOT_TOP.getIndex());
				notice.setRedirectUrl(ModulePathType.PRINCIPAL_MAIL.getUrl() + mail.getId());
				notice.setSenderName(addressName.getName() + "的" + addressName.getRelation_title());
				notice.setSenderId(mail_write.getUserId());
				notice.setSourceId(mail.getId());
				noticeService.insertNoticeSelective(notice);
				int rowNum = 0;
				Tb_noticeUser noticeUser = null;
				// 设置用户标记表
				// 插入园长接收
				for (int i = 0; i < boss.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 默认未读
					noticeUser.setIsRead((byte) 0);
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(boss.get(i).getId());
					noticeUser.setUserType(boss.get(i).getType());
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
					rowNum++;
				}
				// 插入其他家长接收
				for (int i = 0; i < parents.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 设置发送者家长为默认已读，其他家长默认未读
					if (parents.get(i).getParentId().equals(mail_write.getUserId())) {
						noticeUser.setIsRead((byte) 1);
					} else {
						noticeUser.setIsRead((byte) 0);
					}
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(parents.get(i).getParentId());
					noticeUser.setUserType((byte) 30);
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
					rowNum++;
				}
				if (rowNum > 0) {
					back.setBackTypeWithLog(mail.getId(), BackType.SUCCESS_INSERT);
				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=");
				}
				// 回复邮件
			} else {
				mailReply = new Tb_mail_reply();
				data = new Hand_UserAndStudent();
				data.setUserId(mail_write.getUserId());
				data.setStudentId(mail_write.getStudentId());
				// 根据登录者的身份设置发送者称呼
				if (mail_write.getUserType() == 31) {
					Tb_staff staff = mailBoxService.selectStaffById(mail_write.getUserId());
					mailReply.setSenderName(staff.getName() + "(园长)");
				} else if (mail_write.getUserType() == 30) {
					Hand_addressName parent = mailBoxService.selectAddressName(data);
					mailReply.setSenderName(parent.getName() + "的" + parent.getRelation_title());
				} else if (mail_write.getUserType() == 35) {
					Tb_staff staff = mailBoxService.selectStaffById(mail_write.getUserId());
					mailReply.setSenderName(staff.getName() + "(行政)");
				}
				mailReply.setSenderId(mail_write.getUserId());
				// stephen.wang 2017-8-30
				// 内容里面如果有临时图片，执行将临时图片转换为正式图片，上传一开始都是临时图片，当执行保存时，转为正式
				String newContent = FileUtil.transferTempUrl2FormalUrlWithAliOss(mail_write.getContent(), ossInfo);
				mailReply.setContent(newContent);
				mailReply.setCreated(new Date());
				mailReply.setMailId(mail_write.getMailId());
				// 插入回信表
				int rowNum = mailBoxService.replyMail(mailReply);
				Tb_notice notice = noticeService.findNoticeBySourceId(mail_write.getMailId(), (byte) 4).get(0);
				// 刷新其他接收者的时间，并且设置为未读，好让别人回复时，其他人收到新消息后再消息列表会排在前面
				int row = noticeService.refreshTime(notice.getId(), mail_write.getUserId());
				if (rowNum > 0) {
					back.setBackTypeWithLog(mail_write.getMailId(), BackType.SUCCESS_INSERT);
				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=" + row);

				}
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, ex.getMessage());
		}
		return back;
	}

	/**
	 * 家长页面点击园长信箱
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 老师页面点击学生对话", notes = "老师页面点击学生对话")
	@PostMapping(value = "teacherGetMailId/{student_id}/{own_id}")
	@ResponseBody
	public Out<Hand_mailRecord> teacherGetMailId(
			@ApiParam(value = "老师页面点击学生对话", required = true) @PathVariable("student_id") Integer student_id,
			@PathVariable("own_id") Integer own_id) {
		logger.info("MailBoxController:teacherGetMailId,student_id=" + student_id);

		PageInfo<Tb_notice> notices = null;
		int mailId = 0;
		Hand_mailRecord data = new Hand_mailRecord();
		List<Tb_student_parent> parents = null;
		Out<Hand_mailRecord> back = new Out<Hand_mailRecord>();
		try {

			// 查询家长信息
			parents = mailBoxService.findParentByStudentId(student_id);
			System.out.println(parents.get(0).getParentId());			
			notices = noticeService.findNoticeByUserId(1, 5, parents.get(0).getParentId(), (byte) RoleType.PARENT, (byte) 5);

			System.out.println(notices.getList().size());
			if (notices.getList().size() != 0) {
				// 找出邮件的通知
			    
					mailId = notices.getList().get(0).getSourceId();
					System.out.println(mailId);
					data = findReply_ByNoticeId(mailId).getData();
					// 设置为已读
					noticeService.setNoticeRead(own_id, notices.getList().get(0).getId());

				

				back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
			} else {
				back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, ex.getMessage());
		}
		return back;
	}

	/**
	 * 老师家长对话
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 老师家长对话", notes = "老师家长对话")
	@PostMapping(value = "teacherTaklToParent")
	@ResponseBody
	public Out<Integer> teacherTaklToParent(
			@ApiParam(value = "老师家长对话", required = true) @RequestBody In_MailBox_Reply mail_write) {
		logger.info("MailBoxController:teacherTaklToParent,mail_write=" + mail_write);

		Hand_UserAndStudent data = null;
		Tb_mail_reply mailReply = null;
		Hand_addressName addressName = null;
		List<Hand_staff4PhoneBook> teachers = null;
		List<Tb_student_parent> parents = null;
		Out<Integer> back = new Out<Integer>();
		try {

			// 通知id没有时就是写邮件
			if (mail_write.getMailId() == 0) {
				Tb_notice notice = new Tb_notice();
				Tb_mail mail = new Tb_mail();
				if (mail_write.getUserType() == 30) {
					Hand_WholeStudentParents4PhoneBook student = mailListService.findById(mail_write.getStudentId());
					data = new Hand_UserAndStudent();
					data.setStudentId(mail_write.getStudentId());
					data.setUserId(mail_write.getUserId());
					// 通过班级id查找该班级所有老师
					teachers = mailListService.findTeacherByClass(student.getClassId());
					addressName = mailBoxService.selectAddressName(data);
					notice.setSenderName(addressName.getName() + "的" + addressName.getRelation_title());
					mail.setSenderName(addressName.getName() + "的" + addressName.getRelation_title());
				} else if (mail_write.getUserType() == 32) {
					// 查询班级
					Tb_staffData staffClass = mailListService.findClassIdBySid(mail_write.getUserId());
					// 查找本班的老师
					teachers = mailListService.findTeacherByClass(staffClass.getClassId());
					notice.setSenderName(staffClass.getName() + "(老师)");
					mail.setSenderName(staffClass.getName() + "(老师)");
				}
				// 查询家长信息
				parents = mailBoxService.findParentByStudentId(mail_write.getStudentId());
				// 插入邮件表
				logger.info("mail_write.getContent()=" + mail_write.getContent());
				// stephen.wang 2017-8-30
				// 内容里面如果有临时图片，执行将临时图片转换为正式图片，上传一开始都是临时图片，当执行保存时，转为正式
				String newContent = FileUtil.transferTempUrl2FormalUrlWithAliOss(mail_write.getContent(), ossInfo);
				mail.setContent(newContent);
				mail.setCreated(new Date());
				mail.setSenderId(mail_write.getUserId());
				mailBoxService.insertMailSelective(mail);
				// 插入通知表
				notice.setCreated(new Date());
				notice.setNoticeModel(NoticeModel.INSIDE_URL.getIndex());
				notice.setNoticeType(NoticeType.TeacherChat_Parent.getIndex());
				notice.setPriority(NoticeTop.NOT_TOP.getIndex());
				notice.setRedirectUrl(ModulePathType.TeacherChat_Parent.getUrl() + mail.getId());
				notice.setSenderId(mail_write.getUserId());
				notice.setSourceId(mail.getId());
				noticeService.insertNoticeSelective(notice);
				int rowNum = 0;
				Tb_noticeUser noticeUser = null;
				// 设置用户标记表
				// 插入老师接收
				for (int i = 0; i < teachers.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 设置发送老师默认已读
					if (teachers.get(i).getId().equals(mail_write.getUserId())) {
						noticeUser.setIsRead((byte) 1);
					} else {
						noticeUser.setIsRead((byte) 0);
					}
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(teachers.get(i).getId());
					noticeUser.setUserType(teachers.get(i).getType());
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
					rowNum++;
				}
				// 插入其他家长接收
				for (int i = 0; i < parents.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 设置发送者家长为默认已读，其他家长默认未读
					if (parents.get(i).getParentId().equals(mail_write.getUserId())) {
						noticeUser.setIsRead((byte) 1);
					} else {
						noticeUser.setIsRead((byte) 0);
					}
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(parents.get(i).getParentId());
					noticeUser.setUserType((byte) 30);
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
					rowNum++;
				}
				if (rowNum > 0) {
					back.setBackTypeWithLog(mail.getId(), BackType.SUCCESS_INSERT);
				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=");
				}
				// 回复邮件
			} else {
				mailReply = new Tb_mail_reply();
				data = new Hand_UserAndStudent();
				data.setUserId(mail_write.getUserId());
				data.setStudentId(mail_write.getStudentId());
				// 根据登录者的身份设置发送者称呼
				if (mail_write.getUserType() == 32) {
					Tb_staff staff = mailBoxService.selectStaffById(mail_write.getUserId());
					mailReply.setSenderName(staff.getName() + "(老师)");
				} else if (mail_write.getUserType() == 30) {
					Hand_addressName parent = mailBoxService.selectAddressName(data);
					mailReply.setSenderName(parent.getName() + "的" + parent.getRelation_title());
				}
				mailReply.setSenderId(mail_write.getUserId());
				// stephen.wang 2017-8-30
				// 内容里面如果有临时图片，执行将临时图片转换为正式图片，上传一开始都是临时图片，当执行保存时，转为正式
				String newContent = FileUtil.transferTempUrl2FormalUrlWithAliOss(mail_write.getContent(), ossInfo);
				mailReply.setContent(newContent);
				mailReply.setCreated(new Date());
				mailReply.setMailId(mail_write.getMailId());
				// 插入回信表
				int rowNum = mailBoxService.replyMail(mailReply);
				Tb_notice notice = noticeService.findNoticeBySourceId(mail_write.getMailId(), (byte) 5).get(0);
				// 刷新其他接收者的时间，并且设置为未读，好让别人回复时，其他人收到新消息后再消息列表会排在前面
				int row = noticeService.refreshTime(notice.getId(), mail_write.getUserId());
				if (rowNum > 0) {
					back.setBackTypeWithLog(mail_write.getMailId(), BackType.SUCCESS_INSERT);
				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=" + row);

				}
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, ex.getMessage());
		}
		return back;
	}

	/**
	 * 根据邮件id查找关于该条信息及其回复
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findReply_ByNoticeId/{mailId}")
	@ApiOperation(value = "根据邮件id查找关于该条信息及其回复", notes = "根据邮件id查找关于该条信息及其回复")
	@ResponseBody
	public Out<Hand_mailRecord> findReply_ByNoticeId(@PathVariable("mailId") Integer mailId) {
		logger.info("访问  MailBoxController:findReply_ByNoticeId,mailId=" + mailId);
		List<Tb_mail_reply> mails = null;
		Tb_mail mail = null;
		Tb_head_sculpture head = null;
		Hand_mailRecord data = null;
		Out<Hand_mailRecord> back = new Out<Hand_mailRecord>();
		try {
			mail = mailBoxService.findMailById(mailId);
			data = new Hand_mailRecord();
			if (mail != null) {
				String mailTitle = mail.getSenderName();
				String senderName = mailTitle.substring(mailTitle.length() - 2, mailTitle.length());
				//给首条消息设置头像
				if ("爸爸".equals(senderName)) {
					head = HeadMemoryConfigStorageContainer.findByOriId(5);
					mail.setHead(head.getAddress());
				} else if ("妈妈".equals(senderName)) {
					head = HeadMemoryConfigStorageContainer.findByOriId(6);
					mail.setHead(head.getAddress());
				} else if ("爷爷".equals(senderName)) {
					head = HeadMemoryConfigStorageContainer.findByOriId(7);
					mail.setHead(head.getAddress());
				} else if ("奶奶".equals(senderName)) {
					head = HeadMemoryConfigStorageContainer.findByOriId(8);
					mail.setHead(head.getAddress());
				}else if ("师)".equals(senderName)) {
					head = HeadMemoryConfigStorageContainer.findByOriId(9);
					mail.setHead(head.getAddress());
				}else{
					head = HeadMemoryConfigStorageContainer.findByOriId(5);
					mail.setHead(head.getAddress());
				}
				data.setMai(mail);
				mails = mailBoxService.selectMailReplyByNoticeId(mailId);
				// 根据时间排序
				Collections.sort(mails, new Comparator<Tb_mail_reply>() {
					public int compare(Tb_mail_reply o1, Tb_mail_reply o2) {
						return (int) (o1.getCreated().getTime() - o2.getCreated().getTime());
					}
				});
				// 转换时间格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				mail.setDate(sdf.format(mail.getCreated()));
				//给回复的消息设置头像
				for (int i = 0; i < mails.size(); i++) {
					mails.get(i).setDate(sdf.format(mails.get(i).getCreated()));
					String title = mails.get(i).getSenderName();
					String name = title.substring(title.length() - 2, title.length());
					// 设置头像

					if ("长)".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(9);
						mails.get(i).setHead(head.getAddress());
					} else if ("政)".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(9);
						mails.get(i).setHead(head.getAddress());
					} else if ("园长".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(9);
						mails.get(i).setHead(head.getAddress());
					} else if ("爸爸".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(5);
						mails.get(i).setHead(head.getAddress());
					} else if ("妈妈".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(6);
						mails.get(i).setHead(head.getAddress());
					} else if ("爷爷".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(7);
						mails.get(i).setHead(head.getAddress());
					} else if ("奶奶".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(8);
						mails.get(i).setHead(head.getAddress());
					}else if ("师)".equals(name)) {
						head = HeadMemoryConfigStorageContainer.findByOriId(9);
						mails.get(i).setHead(head.getAddress());
					}

				}
				data.setReplys(mails);
				back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
			} else {
				back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage());
		}
		return back;
	}

	/**
	 * 园长删除消息
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "deleteMailByBoss/{mailReplyId}")
	@ApiOperation(value = "园长删除消息", notes = "园长删除消息")
	@ResponseBody
	public Out<String> deleteMailByBoss(@PathVariable("mailReplyId") Integer mailReplyId) {
		logger.info("访问  MailBoxController:deleteMailByBoss,mailReplyId=" + mailReplyId);
		Out<String> back = new Out<String>();
		try {
			int rowNum = mailBoxService.deleteMailReplyByNoticeId(mailReplyId);
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_UPDATE, "rowNum=" + rowNum);
			} else {
				back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, "rowNum=" + rowNum);
			}
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

}
