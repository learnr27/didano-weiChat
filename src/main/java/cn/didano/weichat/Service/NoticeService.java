package cn.didano.weichat.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_noticeMapper;
import cn.didano.weichat.dao.Tb_noticeUserMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Tb_notice;
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
	public List<Tb_notice> findNoticeByUserId(Integer id){
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andUserIdEqualTo(id);
 		condition.setOrderByClause("created");
 		List<Tb_noticeUser> noticeUser= noticeUserMapper.selectByExample(condition);
 		List<Tb_notice> notices = new ArrayList<Tb_notice>();
 		Tb_notice notice = null;
 		//按时间排序
 		for (int i = noticeUser.size()-1; i >=0 ; i--) {
			notice = noticeMapper.selectByPrimaryKey(noticeUser.get(i).getNoticeId());
			notices.add(notice);
		}
 		return notices;
	}
	
}
