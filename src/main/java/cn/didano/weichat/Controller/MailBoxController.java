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
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_MailBox_Reply;
import cn.didano.weichat.json.In_MailBox_Write;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_addressName;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_notice_reply;
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
	public Out<String> write_mail(@ApiParam(value = " 写邮件", required = true) @RequestBody In_MailBox_Write mail_write) {
		logger.info("MailBoxController:write_mail,mail_write=" + mail_write);

		List<Tb_staff> boss = null;
		List<Integer> bossId = new ArrayList<Integer>();
		List<Hand_addressName> addressName=null;
		Out<String> back = new Out<String>();
		try {
			System.out.println(mail_write.getUserId());
			boss = mailBoxService.selectBossByParentId(mail_write.getUserId());
			addressName = mailBoxService.selectAddressName(mail_write.getUserId());
			//获取该学校所有园长id
			for (int i = 0; i < boss.size(); i++) {
				bossId.add(boss.get(i).getId());
			}
			Tb_notice notice = new Tb_notice();
			notice.setContent(mail_write.getContent());
			notice.setNoticeType((byte) 4);
			notice.setPriority((byte) 0);
			notice.setIs_read((byte) 0);
			notice.setCreated(new Date()); 
			notice.setAddresserId(mail_write.getUserId());
			notice.setAddresserName(addressName.get(0).getName()+"的"+addressName.get(0).getRelation_title());
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
	
	/**
	 * 回复邮件
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = "回复邮件", notes = "回复邮件")
	@PostMapping(value = "reply_mail")
	@ResponseBody
	public Out<String> reply_mail(@ApiParam(value = "回复邮件", required = true) @RequestBody In_MailBox_Reply mail_reply) {
		logger.info("MailBoxController:reply_maill,mail_edit=" + mail_reply);	 
        Tb_notice_reply noticeReply =null;
		Out<String> back = new Out<String>();
		try {
			
			noticeReply = new Tb_notice_reply();
			//根据登录者的身份设置发送者称呼
			if(mail_reply.getUserType()==31){
				Tb_staff boss = mailBoxService.selectBossById(mail_reply.getUserId());
				noticeReply.setAddressername(boss.getName()+"园长");
			}else{
				Hand_addressName parent = mailBoxService.selectAddressName(mail_reply.getUserId()).get(0);
				noticeReply.setAddressername(parent .getName()+"的"+parent .getRelation_title());
			}
			noticeReply.setAddresserid(mail_reply.getUserId());			
			noticeReply.setContent(mail_reply.getContent());
			noticeReply.setCreated(new Date());
			noticeReply.setNoticeid(mail_reply.getNoticeId());
			//插入回信表
			int rowNum=mailBoxService.replyMail(noticeReply);
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
	
	/**
	 * 根据通知id查找关于该条信息的回复
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findReply_ByNoticeId/{noticeId}")
	@ApiOperation(value = "根据通知id查找关于该条信息的回复", notes = "根据通知id查找关于该条信息的回复")
	@ResponseBody
	public Out<OutList<Tb_notice_reply>> findReply_ByNoticeId(@PathVariable("noticeId") Integer noticeId) {
		logger.info("访问  MailBoxController:findReply_ByNoticeId,noticeId=" + noticeId);
		List<Tb_notice_reply> notices = null;
		OutList<Tb_notice_reply> outList = null;
		Out<OutList<Tb_notice_reply>> back = new Out<OutList<Tb_notice_reply>>();
		try {
			notices =mailBoxService.selectReplyByNoticeId(noticeId);
			//根据时间排序
			Collections.sort(notices, new Comparator<Tb_notice_reply>(){
	 			 public int compare(Tb_notice_reply o1, Tb_notice_reply o2) {
	 			    return (int)(o2.getCreated().getTime()-o1.getCreated().getTime());
	 			 }
	 		});
			//转换时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
			for (int i = 0; i < notices.size(); i++) {
				notices.get(i).setDate(sdf.format(notices.get(i).getCreated()));
			}
			outList = new OutList<Tb_notice_reply>(notices.size(), notices);
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
