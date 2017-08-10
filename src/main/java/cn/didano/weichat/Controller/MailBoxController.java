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

import cn.didano.weichat.Service.MailBoxService;
import cn.didano.weichat.Service.NoticeService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.constant.ModulePathType;
import cn.didano.weichat.constant.NoticeModel;
import cn.didano.weichat.constant.NoticeTop;
import cn.didano.weichat.constant.NoticeType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_MailBox_Reply;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_UserAndStudent;
import cn.didano.weichat.model.Hand_addressName;
import cn.didano.weichat.model.Hand_mailRecord;
import cn.didano.weichat.model.Tb_head_sculpture;
import cn.didano.weichat.model.Tb_mail;
import cn.didano.weichat.model.Tb_mail_reply;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.repository.HeadMemoryConfigStorageContainer;
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

	/**
	 * 根据园长id,查看园长信箱列表,园长信箱模块
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "mail_findtByBossId/{own_id}/{user_type}")
	@ApiOperation(value = "根据园长id,查看园长信箱列表", notes = "根据园长id,查看园长信箱列表")
	@ResponseBody
	public Out<OutList<Tb_notice>> notice_findtByUserid(@PathVariable("own_id") Integer own_id,
			@PathVariable("user_type") byte user_type) {
		logger.info("访问  NoticeController:notice_findtByUserid,own_id=" + own_id);
		Tb_notice notice = null;
		Tb_head_sculpture head = null;
		List<Tb_notice> mails = null;
		List<Tb_notice> notices = null;
		OutList<Tb_notice> outList = null;
		Out<OutList<Tb_notice>> back = new Out<OutList<Tb_notice>>();
		try {
			mails = new ArrayList<Tb_notice>();
			notices = noticeService.findNoticeByUserId(own_id, user_type);
			// 转换时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = null;
			if (notices.size() != 0) {
				// 获取头像地址
				for (int i = 0; i < notices.size(); i++) {
					notice = notices.get(i);
					if (notice.getNoticeType() == 4) {
						notice.setTitle(notices.get(i).getSenderName().split("的")[0] + "小朋友的家庭");
						head = HeadMemoryConfigStorageContainer.findByOriId(10);
						notice.setHeadUrl(head.getAddress());
						date = sdf.format(notices.get(i).getCreated());
						notice.setDate(date);
						mails.add(notice);
					}
				}
				
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
	public Out<String> write_mail(
			@ApiParam(value = "写邮件，回复邮件", required = true) @RequestBody In_MailBox_Reply mail_write) {
		logger.info("MailBoxController:write_mail,mail_write=" + mail_write);

		Hand_UserAndStudent data = null;
		Tb_mail_reply mailReply = null;
		Hand_addressName addressName = null;
		List<Tb_staff> boss = null;
		List<Tb_student_parent> parents = null;
		List<Integer> receiveId = new ArrayList<Integer>();
		Out<String> back = new Out<String>();
		try {
			// 通知id没有时就是写邮件
			if (mail_write.getMailId() == 0) {
				data = new Hand_UserAndStudent();
				data.setStudentId(mail_write.getStudentId());
				data.setUserId(mail_write.getUserId());
				parents = mailBoxService.findParentByStudentId(mail_write.getStudentId());
				boss = mailBoxService.selectBossByParentId(mail_write.getUserId());
				addressName = mailBoxService.selectAddressName(data);
				// 获取该学校所有园长id
				for (int i = 0; i < boss.size(); i++) {
					receiveId.add(boss.get(i).getId());
				}
				// 获取该学生其他家长id
				for (int i = 0; i < parents.size(); i++) {
					receiveId.add(parents.get(i).getId());
				}
				// 插入邮件表
				Tb_mail mail = new Tb_mail();
				mail.setContent(mail_write.getContent());
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
				notice.setRedirectUrl(ModulePathType.PRINCIPAL_MAIL.getUrl() + "{" + mail.getId() + "}");
				notice.setSenderName(addressName.getName() + "的" + addressName.getRelation_title());
				notice.setSenderId(mail_write.getUserId());
				notice.setSourceId(mail.getId());
				noticeService.insertNoticeSelective(notice);
				int rowNum = 0;
				Tb_noticeUser noticeUser = new Tb_noticeUser();
				// 设置用户标记表
				for (int i = 0; i < receiveId.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 默认未读
					noticeUser.setIsRead((byte) 0);
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(receiveId.get(i));
					noticeUser.setUserType((byte) 31);
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
					rowNum++;
				}
				if (rowNum > 0) {
					back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "rowNum=" + rowNum);

				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=");
				}
				// 回复邮件
			} else {
				mailReply = new Tb_mail_reply();
				data = new Hand_UserAndStudent();
				data.setUserId(mail_write.getUserId());
				// 根据登录者的身份设置发送者称呼
				if (mail_write.getUserType() == 31) {
					Tb_staff staff = mailBoxService.selectBossById(mail_write.getUserId());
					mailReply.setSenderName(staff.getName() + "园长");
					;
				} else {
					Hand_addressName parent = mailBoxService.selectAddressName(data);
					mailReply.setSenderName(parent.getName() + "的" + parent.getRelation_title());
					;
				}
				mailReply.setSenderId(mail_write.getUserId());
				;
				mailReply.setContent(mail_write.getContent());
				mailReply.setCreated(new Date());
				mailReply.setMailId(mail_write.getMailId());
				// 插入回信表
				int rowNum = mailBoxService.replyMail(mailReply);
				Tb_notice notice = noticeService.findNoticeBySourceId(mail_write.getMailId(), (byte) 4).get(0);
				// 刷新其他接收者的时间，并且设置为未读，好让别人回复时，其他人收到新消息后再消息列表会排在前面
				int row = noticeService.refreshTime(notice.getId());
				if (rowNum > 0) {
					back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "rowNum=" + rowNum + ",row=" + row);

				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=");
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
			String mailTitle = mail.getSenderName();
			String senderName = mailTitle.substring(mailTitle.length() - 2, mailTitle.length());
			if ("爸爸".equals(senderName)) {
				head = HeadMemoryConfigStorageContainer.findByOriId(9);
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
			}
			data = new Hand_mailRecord();
			data.setMai(mail);
			mails = mailBoxService.selectMailReplyByNoticeId(mailId);
			// 根据时间排序
			Collections.sort(mails, new Comparator<Tb_mail_reply>() {
				public int compare(Tb_mail_reply o1, Tb_mail_reply o2) {
					return (int) (o1.getCreated().getTime() - o2.getCreated().getTime());
				}
			});
			// 转换时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
			mail.setDate(sdf.format(mail.getCreated()));
			for (int i = 0; i < mails.size(); i++) {
				mails.get(i).setDate(sdf.format(mails.get(i).getCreated()));
				String title = mails.get(i).getSenderName();
				String name = title.substring(title.length() - 2, title.length());
				System.out.println(name);
				// 设置头像
				if ("园长".equals(name)) {
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
				}

			}
			data.setReplys(mails);
			back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
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
