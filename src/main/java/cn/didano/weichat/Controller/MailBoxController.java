package cn.didano.weichat.Controller;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.MailBoxService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_MailBox_Edit;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Tb_mailBox;
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
	
	
	/**
	 * 写邮件
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 写邮件", notes = " 写邮件")
	@PostMapping(value = "write_mail")
	@ResponseBody
	public Out<String> write_mail(
			@ApiParam(value = " 写邮件", required = true) @RequestBody In_MailBox_Edit mail_edit) {
		logger.info("MailBoxController:write_mail,mail_edit=" + mail_edit);

        Tb_mailBox mailBox=null;
        List<Tb_staff> boss = null;
		Out<String> back = new Out<String>();
		try {
			mailBox = new Tb_mailBox();
			BeanUtils.copyProperties(mailBox, mail_edit);
			boss = mailBoxService.selectBossByParentId(mail_edit.getUserId());
			int rowNum = mailBoxService.writeMail(mailBox);
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
}
