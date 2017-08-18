package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_mailMapper;
import cn.didano.weichat.dao.Tb_mail_replyMapper;
import cn.didano.weichat.dao.Tb_noticeMapper;
import cn.didano.weichat.dao.Tb_notice_replyMapper;
import cn.didano.weichat.dao.Tb_staffMapper;
import cn.didano.weichat.dao.Tb_student_parentMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Hand_UserAndStudent;
import cn.didano.weichat.model.Hand_addressName;
import cn.didano.weichat.model.Tb_mail;
import cn.didano.weichat.model.Tb_mail_reply;
import cn.didano.weichat.model.Tb_mail_replyExample;
import cn.didano.weichat.model.Tb_notice_reply;
import cn.didano.weichat.model.Tb_notice_replyExample;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_student_parentExample;

@Service
public class MailBoxService {

	@Autowired
	private Tb_noticeMapper noticeMapper;
	@Autowired
	private Tb_staffMapper staffMapper;
	@Autowired
	private Tb_notice_replyMapper noticeReplyMapper;
	@Autowired
	private Tb_student_parentMapper parentMapper;
	@Autowired
	private Tb_mailMapper mailMapper;
	@Autowired
	private Tb_mail_replyMapper mailReplyMapper;
	
	/**
	 * 插入信箱表
	 */
	public int insertMailSelective(Tb_mail record) {	
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return mailMapper.insertSelective(record);
	}
	/**
	 * 通过邮件id查找邮件
	 */
	public Tb_mail findMailById(Integer mailId){
		System.out.println(111111);
		return mailMapper.selectByPrimaryKey(mailId);
	}
	/**
	 * 根据学生id查找家长
	 */
	public List<Tb_student_parent> findParentByStudentId(Integer studentId){
		Tb_student_parentExample condition = new Tb_student_parentExample();
		Tb_student_parentExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andDeletedEqualTo(false);
 		criteria.andStudentIdEqualTo(studentId);
 		return parentMapper.selectByExample(condition);
	}
	/**
	 * 插入回复表
	 */
	public int replyMail(Tb_mail_reply record){
		return mailReplyMapper.insertSelective(record);
	}
	/**
	 * 查找园长
	 */
	 public Tb_staff selectBossById(Integer userId){
		 return staffMapper.selectByPrimaryKey(userId);
	 }
	/**
	 * 通过家长id查询学校所有园长
	 */
	public List<Tb_staff> selectBossByParentId(Integer userId){
		return noticeMapper.selectBossByParentId(userId);
	}
	/**
	 * 通过家长id查询该家长为发送者时候的称呼
	 */
	public  Hand_addressName selectAddressName(Hand_UserAndStudent record){
		
		return noticeMapper.selectAddressName(record);
	}
	/**
	 * 根据通知id查找该通知的回复
	 */
	public List<Tb_notice_reply> selectReplyByNoticeId(Integer noticeId){
		Tb_notice_replyExample condition = new Tb_notice_replyExample();
		Tb_notice_replyExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andNoticeidEqualTo(noticeId);
 		criteria.andDeletedEqualTo(false);
 		return noticeReplyMapper.selectByExample(condition);
	}
	/**
	 * 根据通知id查找该通知的回复
	 */
	public List<Tb_mail_reply> selectMailReplyByNoticeId(Integer mailId){
		Tb_mail_replyExample condition = new Tb_mail_replyExample();
		Tb_mail_replyExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andMailIdEqualTo(mailId);
 		criteria.andDeletedEqualTo(false);
 		return mailReplyMapper.selectByExample(condition);
	}
	/**
	 * 删除邮件回复
	 */
	public int deleteMailReplyByNoticeId(Integer mailReplyId){
		
		Tb_mail_reply reply = mailReplyMapper.selectByPrimaryKey(mailReplyId);
		reply.setDeleted(true);
 		return mailReplyMapper.updateByPrimaryKeySelective(reply);
	}
}
