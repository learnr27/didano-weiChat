package cn.didano.weichat.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.MailBoxService;
import cn.didano.weichat.Service.NoticeService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_MailBox_Edit;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_staff;
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
	 * 写邮件
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 写邮件", notes = " 写邮件")
	@PostMapping(value = "write_mail")
	@ResponseBody
	public Out<String> write_mail(@ApiParam(value = " 写邮件", required = true) @RequestBody In_MailBox_Edit mail_edit) {
		logger.info("MailBoxController:write_mail,mail_edit=" + mail_edit);

		List<Tb_staff> boss = null;
		List<Integer> bossId = new ArrayList<Integer>();
		Out<String> back = new Out<String>();
		try {
			System.out.println(mail_edit.getUserId());
			boss = mailBoxService.selectBossByParentId(mail_edit.getUserId());
			for (int i = 0; i < boss.size(); i++) {
				bossId.add(boss.get(i).getId());
			}
			Tb_notice notice = new Tb_notice();
			notice.setContent(mail_edit.getContent());
			notice.setNoticeType((byte) 4);
			notice.setPriority((byte) 0);
			notice.setIs_read((byte) 0);
			notice.setCreated(new Date());
			// 判断用户类型
			if (boss.size() == 1) {
				// 单个用户
				notice.setPersonType((byte) 1);
			} else {
				// 多个用户
				notice.setPersonType((byte) 2);
			}
			// 插入通知表
			noticeService.insertNoticeSelective(notice);
			int rowNum = 0;
			Tb_noticeUser noticeUser = new Tb_noticeUser();
			// 设置用户标记表
			for (int i = 0; i < boss.size(); i++) {
				noticeUser = new Tb_noticeUser();
				// 默认未读
				noticeUser.setIsRead((byte) 0);
				noticeUser.setNoticeId(notice.getId());
				noticeUser.setUserId(boss.get(i).getId());
				noticeUser.setUserType((byte)31);
				noticeUser.setCreated(new Date());
				noticeService.insertNoticeUserSelective(noticeUser);
				rowNum++;
			}
			if (rowNum>0) {
				back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "rowNum="+rowNum);

			} else {
				// 更新有问题
				back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "rowNum=");
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
}
