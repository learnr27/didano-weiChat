package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.ClassService;
import cn.didano.weichat.Service.MailBoxService;
import cn.didano.weichat.Service.MailListService;
import cn.didano.weichat.Service.NoticeService;
import cn.didano.weichat.Service.PrincipalNoticeService;
import cn.didano.weichat.Service.StaffService;
import cn.didano.weichat.config.OssInfo;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.constant.ModulePathType;
import cn.didano.weichat.constant.StaffType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_Notice_Edit;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_WholeStudentParents4PhoneBook;
import cn.didano.weichat.model.Hand_parent4mailList;
import cn.didano.weichat.model.Hand_staff4PhoneBook;
import cn.didano.weichat.model.Tb_classStudent;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_principal_notice;
import cn.didano.weichat.model.Tb_schoolParent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_staffData;
import cn.didano.weichat.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "校园通知服务", tags = "校园通知服务，提供给表现层")
@RestController
@RequestMapping(value = "/base/principal/post/")
public class PrincipalNoticeController {

	static Logger logger = Logger.getLogger(PrincipalNoticeController.class);
	@Autowired
	private PrincipalNoticeService principalNoticeService;
	@Autowired
	private MailBoxService mailBoxService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MailListService mailListService;
	@Autowired
	private ClassService classService;
	@Autowired
	private StaffService staffService;
	@Autowired
	OssInfo ossInfo;

	/**
	 * 老师查询该班级学生信息
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "student_searchByClass/{staff_id}")
	@ApiOperation(value = "老师查询该班级学生信息", notes = "老师查询该班级学生信息")
	@ResponseBody
	public Out<Tb_classStudent> student_searchByTeacher(@PathVariable("staff_id") Integer staff_id) {
		logger.info("访问 PrincipalNoticeController:student_searchByTeacher,staff_id=" + staff_id);
		// 获取当前登录人员的信息
		Tb_staff staff = mailBoxService.selectStaffById(staff_id);
		// 获取当前登录老师的班级id
		Tb_staffData classid = mailListService.findClassIdBySid(staff_id);
		List<Hand_WholeStudentParents4PhoneBook> students = null;
		Tb_classStudent data = new Tb_classStudent();
		Out<Tb_classStudent> back = new Out<Tb_classStudent>();
		try {
			if (staff.getType() == StaffType.TEACHEER.getIndex()) {
				// 获取当前老师所带的所有学生
				students = mailListService.findByTeacher(staff.getId());
				if (!students.isEmpty()) {
					// 添加每个学生对应的父母信息
					for (Hand_WholeStudentParents4PhoneBook list : students) {
						List<Hand_parent4mailList> parent = mailListService.findparent(list.getId());
						list.getParent().addAll(parent);
					}

					data.getStudent().addAll(students);
				}
				data.setClassId(classid.getClassId());
				data.setClassName(classService.selectNameByPrimaryKey(classid.getClassId()));

			}
			back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);

		} catch (Exception e) {
			logger.warn(e.getMessage());
			back.setBackType(BackType.INFO_INVOCATIONTARGETEXCEPTION, e.getMessage());
		}
		return back;
	}

	/**
	 * 查看校园通知列表
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "principalNoticeFindById/{own_id}/{user_type}")
	@ApiOperation(value = "查看园长通知列表", notes = "查看园长通知列表")
	@ResponseBody
	public Out<OutList<Tb_notice>> principalNoticeFindById(@PathVariable("own_id") Integer own_id,
			@PathVariable("user_type") byte user_type) {
		logger.info("访问  PrincipalNoticeController:principalNoticeFindById,own_id=" + own_id);
		Tb_notice notice = null;
		List<Tb_notice> principalNotices = null;
		List<Tb_notice> notices = null;
		OutList<Tb_notice> outList = null;
		Out<OutList<Tb_notice>> back = new Out<OutList<Tb_notice>>();
		try {
			principalNotices = new ArrayList<Tb_notice>();
			notices = noticeService.findNoticeByUserId(own_id, user_type);

			if (notices.size() != 0) {
				// 转换时间格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String date = null;
				if (notices.size() != 0) {
					for (int i = 0; i < notices.size(); i++) {
						notice = notices.get(i);
						if (notice.getNoticeType() == 1) {
							date = sdf.format(notices.get(i).getCreated());
							notice.setDate(date);
							principalNotices.add(notice);
						}
					}

					outList = new OutList<Tb_notice>(principalNotices.size(), principalNotices);
					back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
				}
			} else {
				outList = new OutList<Tb_notice>(principalNotices.size(), principalNotices);
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
	 * 发布校园通知
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = "发布园长通知，班级通知", notes = "发布园长通知，班级通知")
	@PostMapping(value = "publish_notice")
	@ResponseBody
	public Out<String> publish_notice(
			@ApiParam(value = "发布园长通知，班级通知", required = true) @RequestBody In_Notice_Edit notice_edit) {
		logger.info("访问  NoticeController:publish_notice,notice_e=" + notice_edit);

		Tb_principal_notice pricipalNotice = null;
		Tb_notice notice = null;
		Tb_noticeUser noticeUser = null;
		Tb_staff staff = null;
		List<Tb_schoolParent> parents = null;
		List<Hand_staff4PhoneBook> classStaffs = null;
		List<Hand_staff4PhoneBook> staffs = new ArrayList<Hand_staff4PhoneBook>();
		List<Hand_staff4PhoneBook> serveries = null;
		List<Tb_staff> boss = null;
		List<Integer> classId = null;
		List<Integer> parentsId = new ArrayList<Integer>();
		List<Integer> studentId = new ArrayList<Integer>();
		List<Hand_parent4mailList> studentParent = null;
		Map<Integer, List<Tb_schoolParent>> map = new HashMap<Integer, List<Tb_schoolParent>>();
		Map<Integer, List<Hand_parent4mailList>> parentMap = new HashMap<Integer, List<Hand_parent4mailList>>();
		Out<String> back = new Out<String>();
		try {
			// 查询当前登陆人员身份
			staff = mailBoxService.selectStaffById(notice_edit.getOnlineId());
			classId = notice_edit.getClassIdOrStudentId();
			notice = new Tb_notice();
			pricipalNotice = new Tb_principal_notice();
			BeanUtils.copyProperties(notice, notice_edit);
			BeanUtils.copyProperties(pricipalNotice, notice_edit);
			System.out.println(staff.getType());
			if (staff.getType() == 31 || staff.getType() == 35 ) {// 当登录者为园长或者行政时则为发布园长通知
				// 按班级给父母职工分类
				if (notice_edit.getAllStaff() == 0) {// 判断发布范围是否为仅员工
					for (int i = 0; i < classId.size(); i++) {
						parents = mailListService.findParentByClass(classId.get(i));
						classStaffs = mailListService.findTeacherByClass(classId.get(i));
						map.put(classId.get(i), parents);
						staffs.addAll(classStaffs);
					}
					//查找该学校的行政
					serveries = staffService.selectSchoolAllAdministrationInteger(staff.getSchoolId());
					staffs.addAll(serveries);
					// 将家长id放入一个集合
					Set<Integer> keys = map.keySet();
					Iterator<Integer> it = keys.iterator();
					while (it.hasNext()) {
						int key = it.next();
						for (int i = 0; i < map.get(key).size(); i++) {
							parentsId.add(map.get(key).get(i).getId());
						}
					}

					// 设置发布范围
					notice.setPublicationScope("全园");
				} else {// 发布范围仅为员工时
					staffs = staffService.selectSchoolAllStaff(staff.getSchoolId());
					// 设置发布范围
					notice.setPublicationScope("职工");
				}
				// 查询本校其他园长
				boss = mailBoxService.findBossBySchoolId(staff.getSchoolId());
				// 设置发送者身份
				if(staff.getType() == 31){
				notice.setSenderName(staff.getName() + "(园长)");
				pricipalNotice.setSenderName(staff.getName() + "(园长)");
				}else{
					notice.setSenderName(staff.getName() + "(行政)");
					pricipalNotice.setSenderName(staff.getName() + "(行政)");
				}
			} else  if(staff.getType() == 32){// 当登录者为老师时则为发送班级通知
				//查询班级
				Tb_staffData staffClass = mailListService.findClassIdBySid(notice_edit.getOnlineId());
				// 设置发布范围
				notice.setPublicationScope(classService.selectNameByPrimaryKey(staffClass.getClassId()));
				studentId = notice_edit.getClassIdOrStudentId();
				// 根据学生分类把家长放入一个map
				for (int i = 0; i < studentId.size(); i++) {
					studentParent = mailListService.findparent(studentId.get(i));
					parentMap.put(studentId.get(i), studentParent);
				}

				// 将家长id放入一个集合
				Set<Integer> keys = parentMap.keySet();
				Iterator<Integer> it = keys.iterator();
				while (it.hasNext()) {
					int key = it.next();
					for (int i = 0; i < parentMap.get(key).size(); i++) {
						parentsId.add(parentMap.get(key).get(i).getId());
					}
				}
				//查找本班的老师
				staffs = mailListService.findTeacherByClass(staffClass.getClassId());
				// 设置发送者身份
				notice.setSenderName(staff.getName() + "(老师)");
				pricipalNotice.setSenderName(staff.getName() + "(老师)");
			}
			pricipalNotice.setSenderId(notice_edit.getOnlineId());
			pricipalNotice.setCreated(new Date());
			logger.info("pricipalNotice.getContent()="+pricipalNotice.getContent());
			//stephen.wang 2017-8-30
			//内容里面如果有临时图片，执行将临时图片转换为正式图片，上传一开始都是临时图片，当执行保存时，转为正式
			String newContent = FileUtil.transferTempUrl2FormalUrlWithAliOss(pricipalNotice.getContent(), ossInfo);
			pricipalNotice.setContent(newContent);
			notice.setContent(newContent);
			logger.info("newContent="+newContent);
			// 插入园长通知表
			principalNoticeService.insertSelective(pricipalNotice);
			notice.setCreated(new Date());
			// 设置通知模型
			notice.setNoticeModel(notice_edit.getNoticeModel());
			notice.setSenderId(notice_edit.getOnlineId());
			// 设置通知类型
			notice.setNoticeType((byte) 1);
			notice.setSourceId(pricipalNotice.getId());
			// 设置内部url或者外部url
			if (notice_edit.getNoticeModel() == 1) {
				// 设置转向地址
				notice.setRedirectUrl(ModulePathType.PRINCIPAL_NOTICE.getUrl() + "{" + pricipalNotice.getId() + "}");
			} else if (notice_edit.getNoticeModel() == 2) {
				notice.setRedirectUrl(notice_edit.getUrl());
			}
			// 插入通知表
			int rowNum = noticeService.insertNoticeSelective(notice);

			// 插入家长消息列表
			if (notice_edit.getAllStaff() == 0) {// 判断发布范围是否为仅员工
				for (int i = 0; i < parentsId.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 默认未读
					noticeUser.setIsRead((byte) 0);
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(parentsId.get(i));
					noticeUser.setUserType((byte) 30);
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
				}
			}
			// 插入职工消息列表
			if (staffs.size() != 0) {
				for (int i = 0; i < staffs.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 默认未读
					if (staffs.get(i).getId().equals(notice_edit.getOnlineId())) {
						noticeUser.setIsRead((byte) 1);
					} else {
						noticeUser.setIsRead((byte) 0);
					}
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(staffs.get(i).getId());
					noticeUser.setUserType(staffs.get(i).getType());
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
				}
			}
			// 插入其他园长信息
			if (boss != null) {
				for (int i = 0; i < boss.size(); i++) {
					noticeUser = new Tb_noticeUser();
					// 默认未读
					if (boss.get(i).getId().equals(notice_edit.getOnlineId())) {
						noticeUser.setIsRead((byte) 1);
					} else {
						noticeUser.setIsRead((byte) 0);
					}
					noticeUser.setNoticeId(notice.getId());
					noticeUser.setUserId(boss.get(i).getId());
					noticeUser.setUserType(boss.get(i).getType());
					noticeUser.setCreated(new Date());
					noticeService.insertNoticeUserSelective(noticeUser);
				}
			}
			// List<Tb_websocket_channel> noticeChannel =
			// websocketService.selcetChannelByType((byte)0);
			// // 广播通知 websocket
			// noticeService.broadcast(noticeChannel.get(0).getChannel());
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "Id=" + "," + ":rowNum=" + rowNum);

			} else {
				// 更新有问题
				back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=" + rowNum);
			}
			// end else

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
	 * 根据校园通知id查询通知
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findprincipalNoticeByprincipalId/{principalId}")
	@ApiOperation(value = "根据园长班级通知id查询通知", notes = "根据园长班级通知id查询通知")
	@ResponseBody
	public Out<OutList<Tb_principal_notice>> findReply_ByNoticeId(@PathVariable("principalId") Integer principalId) {
		logger.info("访问 PrincipalNoticeController:findprincipalNoticeByprincipalId,principalId}=" + principalId);

		List<Tb_principal_notice> principalNotices = null;
		OutList<Tb_principal_notice> outList = null;
		Out<OutList<Tb_principal_notice>> back = new Out<OutList<Tb_principal_notice>>();
		try {
			principalNotices = principalNoticeService.selectById(principalId);
			outList = new OutList<Tb_principal_notice>(principalNotices.size(), principalNotices);
			back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);

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
}
