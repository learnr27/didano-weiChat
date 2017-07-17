package cn.didano.weichat.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_noticeMapper;
import cn.didano.weichat.dao.Tb_noticeUserMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeExample;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_noticeUserExample;

@Service
public class NoticeService {

	@Autowired
	private Tb_noticeMapper noticeMapper;
	@Autowired
	private Tb_noticeUserMapper noticeUserMapper;
	
	/**
	 * 插入tb_notice
	 */
	public int insertNoticeSelective(Tb_notice record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return noticeMapper.insertSelective(record);
	}
	
	/**
	 * 插入tb_notice_user
	 */
	public int insertNoticeUserSelective(Tb_noticeUser record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return noticeUserMapper.insertSelective(record);
	}
	
	/**
	 * 根据用户id查找消息列表
	 */
	public List<Tb_notice> findNoticeByUserId(Integer id,byte type){
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andUserIdEqualTo(id);
 		criteria.andDeletedEqualTo(false);
 		criteria.andUserTypeEqualTo(type);
 		condition.setOrderByClause("created");
 		List<Tb_noticeUser> noticeUser= noticeUserMapper.selectByExample(condition);
 		List<Tb_notice> notices = new ArrayList<Tb_notice>();
 		List<Integer> priority=new ArrayList<Integer>();
 		Tb_notice notice = null;
 		
 		//查询出置顶的消息
 		for (int i = 0; i < noticeUser.size(); i++) {
 			notice = noticeMapper.selectByPrimaryKey(noticeUser.get(i).getNoticeId());
 			if(notice.getPriority()==1){
 				priority.add(i);
 			}
		}
 		for (int i = 0; i < priority.size(); i++) {
 			notice = noticeMapper.selectByPrimaryKey(noticeUser.get(i).getNoticeId());
 			notices.add(notice);
 			//去除已经置顶的消息
 			noticeUser.remove(i);
		}
 		
 		//按时间排序
 		for (int i = noticeUser.size()-1; i >=0 ; i--) {
			notice = noticeMapper.selectByPrimaryKey(noticeUser.get(i).getNoticeId());
			notices.add(notice);
		}
 		//倒序
 		Collections.reverse(notices);
 		return notices;
	}
	/**
	 * 根据通知id删除该通知消息下的用户消息
	 */
	public int deleteNoticeUserByNoticeId(Integer noticeId){
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
		criteria.andNoticeIdEqualTo(noticeId);
		criteria.andDeletedEqualTo(false);
		List<Tb_noticeUser> noticeUsers = noticeUserMapper.selectByExample(condition);
		Tb_noticeUser noticeUser = null;
		int row=0;
		for (int i = 0; i <noticeUsers.size(); i++) {
			noticeUser = noticeUsers.get(i);
			noticeUser.setDeleted(true);
			noticeUserMapper.updateByPrimaryKeySelective(noticeUser);
			row++;
		}
		return row;
				
	}
	
	/**
	 * 通告通知id删除通知信息
	 */
	public int deleteNoticeById(Integer id){
		Tb_notice notice = noticeMapper.selectByPrimaryKey(id);
		notice.setDeleted(true);
		return noticeMapper.updateByPrimaryKeySelective(notice);
	}
	
	/**
	 * 设置消息已读
	 */
	public int setNoticeRead(Integer userId,Integer noticeId){
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andUserIdEqualTo(userId);
 		criteria.andNoticeIdEqualTo(noticeId);
 		criteria.andDeletedEqualTo(false);
 		List<Tb_noticeUser> noticeUser = noticeUserMapper.selectByExample(condition);
 		Tb_noticeUser user = noticeUser.get(0);
 		user.setIsRead((byte)1);
 		return noticeUserMapper.updateByPrimaryKeySelective(user);
	}
}
