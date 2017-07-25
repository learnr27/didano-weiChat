package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.NoticeService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_Notice_Edit;
import cn.didano.weichat.json.In_Read_Date;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Tb_head_sculpture;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
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
		Out<String> back = new Out<String>();

		try {
			notice = new Tb_notice();
			BeanUtils.copyProperties(notice, notice_edit);
			String content = notice_edit.getContent();
			String content_preview = null;
			// 截取消息预览
			if (content.length() >= 20) {
				content_preview = content.substring(0, 20);
			} else {
				content_preview = content.substring(0);
			}
			notice.setContentPreview(content_preview);
			int num = notice_edit.getUserId().size();
			// 判断用户类型
			if (num == 1) {
				// 单个用户
				notice.setPersonType((byte) 1);
			} else {
				// 多个用户
				notice.setPersonType((byte) 2);
			}
			notice.setCreated(new Date());
			// 广播通知
			noticeService.broadcast(notice);
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
	public Out<OutList<Tb_notice>> notice_findtByUserid(@PathVariable("user_id") Integer user_id,@PathVariable("user_type") byte user_type) {
		logger.info("访问  NoticeController:notice_findtByUserid,user_id=" + user_id);
		Tb_notice notice = null;
		Tb_head_sculpture head = null;
		List<Tb_notice> notices = null;
		OutList<Tb_notice> outList = null;
		Out<OutList<Tb_notice>> back = new Out<OutList<Tb_notice>>();
		try {
			notices = noticeService.findNoticeByUserId(user_id,user_type);
			//获取头像地址
			for (int i = 0; i < notices.size(); i++) {
				notice=notices.get(i);
				head = noticeService.selectHeadByNoticeType(notice.getNoticeType()).get(0);
				notice.setHeadUrl(head.getAddress());
			}
			//转换时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String date = null;
			for (int i = 0; i < notices.size(); i++) {
				date = sdf.format(notices.get(i).getCreated());
				notices.get(i).setDate(date);
			}
			outList = new OutList<Tb_notice>(notices.size(), notices);
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
			int rowNum=noticeService.deleteNoticeUserByNoticeId(notice_id);
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
	 *
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
			int rowNum=noticeService.deleteNoticeByUserId(date);
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
			int rowNum=noticeService.setNoticeRead(date.getUserId(), date.getNoticeId());
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
