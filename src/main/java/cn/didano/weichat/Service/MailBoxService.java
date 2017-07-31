package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_noticeMapper;
import cn.didano.weichat.dao.Tb_notice_replyMapper;
import cn.didano.weichat.dao.Tb_staffMapper;
import cn.didano.weichat.model.Hand_addressName;
import cn.didano.weichat.model.Tb_notice_reply;
import cn.didano.weichat.model.Tb_staff;

@Service
public class MailBoxService {

	@Autowired
	private Tb_noticeMapper noticeMapper;
	@Autowired
	private Tb_staffMapper staffMapper;
	@Autowired
	private Tb_notice_replyMapper noticeReplyMapper;
	/**
	 * 插入回复表
	 */
	public int replyMail(Tb_notice_reply record){
		return noticeReplyMapper.insertSelective(record);
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
	public  List<Hand_addressName> selectAddressName(Integer userId){
		return noticeMapper.selectAddressName(userId);
	}
}
