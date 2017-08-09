package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import cn.didano.weichat.Service.WebSocketService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_Notice_Edit;
import cn.didano.weichat.json.In_Read_Date;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Hand_homeMailBox;
import cn.didano.weichat.model.Hand_noticeList;
import cn.didano.weichat.model.Tb_head_sculpture;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_websocket_channel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "消息通知服务", tags = "消息通知服务，提供给表现层")
@RestController
@RequestMapping(value = "/base/notice/post/")
public class NoticeController {

	static Logger logger = Logger.getLogger(NoticeController.class);
	@Autowired
	private NoticeService noticeService;

	@Autowired
	private MailBoxService mailService;

	@Autowired
	private WebSocketService websocketService;

	/**
	 * 发布通知
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = "发布通知", notes = "发布通知")
	@PostMapping(value = "publish_notice")
	@ResponseBody
	public Out<String> publish_notice(
			@ApiParam(value = "发布通知", required = true) @RequestBody In_Notice_Edit notice_edit) {
		logger.info("访问  NoticeController:publish_notice,notice_e=" + notice_edit);

		Tb_notice notice = null;
		Tb_noticeUser noticeUser = null;
		Tb_staff staff = null;
		Out<String> back = new Out<String>();
		try {
			staff = mailService.selectBossById(notice_edit.getOnlineId());
			notice = new Tb_notice();
			BeanUtils.copyProperties(notice, notice_edit);
			// 设置发送者身份
			notice.setSenderId(notice_edit.getOnlineId());
			if (staff.getType() == 31) {
				notice.setSenderName(staff.getName() + "园长");
			} else if (staff.getType() == 32) {
				notice.setSenderName(staff.getName() + "老师");
			}
			int num = notice_edit.getUserId().size();
//			// 判断用户类型
//			if (num == 1) {
//				// 单个用户
//				notice.setPersonType((byte) 1);
//			} else {
//				// 多个用户
//				notice.setPersonType((byte) 2);
//			}
			notice.setCreated(new Date());
			notice.setNoticeModel(notice_edit.getNoticeModel());
			notice.setNoticeType(notice_edit.getNoticeType());
			if(notice_edit.getNoticeModel()==1){
				
			}else if(notice_edit.getNoticeModel()==2){
				notice.setRedictUrl(notice_edit.getUrl());
			}
			// 插入通知表
			noticeService.insertNoticeSelective(notice);
			int rowNum = 0;
			// 设置用户标记表
			for (int i = 0; i < num; i++) {
				noticeUser = new Tb_noticeUser();
				// 默认未读
				noticeUser.setIsRead((byte) 0);
				noticeUser.setNoticeId(notice.getId());
				noticeUser.setUserId(notice_edit.getUserId().get(i));
				noticeUser.setUserType(notice_edit.getUser_type());
				noticeUser.setCreated(new Date());
				noticeService.insertNoticeUserSelective(noticeUser);
				rowNum++;
			}
			List<Tb_websocket_channel> noticeChannel = websocketService.selcetChannelByType((byte)0);
			
			// 广播通知 websocket
			noticeService.broadcast(noticeChannel.get(0).getChannel());
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "Id=" + "," + ":rowNum=" + rowNum);

			} else {
				// 更新有问题
				back.setBackTypeWithLog(BackType.FAIL_INSERT_NO_INSERT, "rowNum=" + rowNum);
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
	 * 根据用户id,用户类型查找消息列表
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "notice_findtByUserid/{user_id}/{user_type}")
	@ApiOperation(value = "根据用户id,用户类型查找消息列表", notes = "根据用户id,用户类型查找消息列表")
	@ResponseBody
	public Out<Hand_noticeList> notice_findtByUserid(@PathVariable("user_id") Integer user_id,
			@PathVariable("user_type") byte user_type) {
		logger.info("访问  NoticeController:notice_findtByUserid,user_id=" + user_id);
		Tb_notice notice = null;
		Tb_head_sculpture head = null;
		List<Tb_notice> notices = null;
		Hand_noticeList data = null;
		List<Hand_homeMailBox> boxs = null;
		Hand_homeMailBox box = null;
		Out<Hand_noticeList> back = new Out<Hand_noticeList>();
		try {
			 boxs = new ArrayList<Hand_homeMailBox>();
			data = new Hand_noticeList();
			notices = noticeService.findNoticeByUserId(user_id, user_type);
			if(notices.size()!=0){
			// 获取头像地址
			for (int i = 0; i < notices.size(); i++) {
				notice = notices.get(i);
				if(notice.getNoticeType()!=4){
				head = noticeService.selectHeadByNoticeType(notice.getNoticeType()).get(0);
				notice.setHeadUrl(head.getAddress());
				}else{
					String title =notice.getSenderName();
					if(title.length()!=0){
					String name = title.substring(title.length()-2, title.length());
					if("园长".equals(name)){
						head = noticeService.selectHeadByNoticeType((byte)9).get(0);
						notices.get(i).setHeadUrl(head.getAddress());
					}else if("爸爸".equals(name)){
						head = noticeService.selectHeadByNoticeType((byte)5).get(0);
						notices.get(i).setHeadUrl(head.getAddress());
					}else if("妈妈".equals(name)){
						head = noticeService.selectHeadByNoticeType((byte)6).get(0);
						notices.get(i).setHeadUrl(head.getAddress());
					}else if("爷爷".equals(name)){
						head = noticeService.selectHeadByNoticeType((byte)7).get(0);
						notices.get(i).setHeadUrl(head.getAddress());
					}else if("奶奶".equals(name)){
						head = noticeService.selectHeadByNoticeType((byte)8).get(0);
						notices.get(i).setHeadUrl(head.getAddress());
					}
					}
				}
			}
			// 转换时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = null;
			List<Integer> removeId= new ArrayList<Integer>();
			for (int i = 0; i < notices.size(); i++) {
				date = sdf.format(notices.get(i).getCreated());
				notices.get(i).setDate(date);
				//挑出园长信箱的通知消息单独为一个集合
				if(notices.get(i).getNoticeType()==4){
					box = new Hand_homeMailBox();
					box.setName(notices.get(i).getSenderName().split("的")[0]+"小朋友的家庭");
					head = noticeService.selectHeadByNoticeType((byte)10).get(0);
					box.setHead(head.getAddress());
					box.getMailBox().add(notices.get(i));
					boxs.add(box);
					removeId.add(i);
				}
			}
			for (int i = 0; i < removeId.size(); i++) {
				notices.remove(removeId.get(i));
			}
			//设置园长信箱数据
			data.setMailBox(boxs);
			//设置消息列表数据
			data.setNotices(notices);
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
	 *
	 * 撤回通知消息
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "withdrawnNotice/{notice_id}")
	@ApiOperation(value = " 撤回通知消息", notes = "撤回通知消息")
	@ResponseBody
	public Out<String> withdrawnNotice(@PathVariable("notice_id") Integer notice_id) {
		logger.info("访问  NoticeController:withdrawnNotice,notice_id=" + notice_id);
		Out<String> back = new Out<String>();
		try {
			noticeService.deleteNoticeById(notice_id);
			int rowNum = noticeService.deleteNoticeUserByNoticeId(notice_id);
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "rowNum=" + rowNum);
			} else {
				back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "rowNum=" + rowNum);
			}
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

	/**
	 *0
	 * 用户删除自己的消息
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "deleteNoticeByUser")
	@ApiOperation(value = "  用户删除自己的消息", notes = "  用户删除自己的消息")
	@ResponseBody
	public Out<String> deleteNoticeByUser(@RequestBody In_Read_Date date) {
		logger.info("访问  NoticeController:deleteNoticeByUser,date=" + date);
		Out<String> back = new Out<String>();
		try {
			int rowNum = noticeService.deleteNoticeByUserId(date);
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

	/**
	 *
	 * 设置消息已读
	 *
	 * @param teacher_id
	 * @return
	 */
	@PostMapping(value = "setNoticeRead")
	@ApiOperation(value = "  设置消息已读", notes = "  设置消息已读")
	@ResponseBody
	public Out<String> setNoticeRead(@RequestBody In_Read_Date date) {
		logger.info("访问  NoticeController:setNoticeRead,date=" + date);
		Out<String> back = new Out<String>();
		try {
			int rowNum = noticeService.setNoticeRead(date.getUserId(), date.getNoticeId());
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
