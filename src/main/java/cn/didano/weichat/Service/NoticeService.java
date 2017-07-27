package cn.didano.weichat.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.didano.weichat.dao.Tb_head_sculptureMapper;
import cn.didano.weichat.dao.Tb_noticeMapper;
import cn.didano.weichat.dao.Tb_noticeUserMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_Read_Date;
import cn.didano.weichat.model.Tb_head_sculpture;
import cn.didano.weichat.model.Tb_head_sculptureExample;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_noticeUserExample;

@Service
public class NoticeService {
	static Logger logger = Logger.getLogger(NoticeService.class);
	@Autowired
	private Tb_noticeMapper noticeMapper;
	@Autowired
	private Tb_noticeUserMapper noticeUserMapper;
	@Autowired
	private Tb_head_sculptureMapper headMapper;

	@Autowired
	private SimpMessagingTemplate messageTemplate;

	// 需要添加用户参数，作为发布对象过滤 //@SendTo("/topic/message")  添加一个用户id列表，返回给前端，前端根据用户id判断是否加入websocket频道，以此接受消息
	public void broadcast(Tb_notice notice) throws Exception { // convert from
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(false);
		criteria.andNoticeIdEqualTo(notice.getId());
		List<Tb_noticeUser> users = noticeUserMapper.selectByExample(condition);
		List<Integer> userId = new ArrayList<Integer>();
		for (int i = 0; i < users.size(); i++) {
			userId.add(users.get(i).getUserId());
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(userId);
		logger.info("jsonInString="+jsonInString);
		this.messageTemplate.convertAndSend("/topic/message",jsonInString);
		return;
	}


	/**
	 * 根据通知类型查找头像
	 */
	public List<Tb_head_sculpture> selectHeadByNoticeType(byte type) {
		Tb_head_sculptureExample condition = new Tb_head_sculptureExample();
		Tb_head_sculptureExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(false);
		criteria.andNoticeTypeEqualTo(type);
		return headMapper.selectByExample(condition);
	}

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
	public List<Tb_notice> findNoticeByUserId(Integer id, byte type) {
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andUserIdEqualTo(id);
		criteria.andDeletedEqualTo(false);
		criteria.andUserTypeEqualTo(type);
		condition.setOrderByClause("created");
		List<Tb_noticeUser> noticeUser = noticeUserMapper.selectByExample(condition);
		List<Tb_notice> notices = new ArrayList<Tb_notice>();
		Tb_notice notice = null;

		// 查询出置顶的消息
		for (int i = 0; i < noticeUser.size(); i++) {
			notice = noticeMapper.selectByPrimaryKey(noticeUser.get(i).getNoticeId());
			// 查询出未读状态
			notice.setIs_read(noticeUser.get(i).getIsRead());
			if (notice.getPriority() == 1) {
				notices.add(notice);
				// 去除已经置顶的消息
				noticeUser.remove(i);

			}
		}
		//倒序
        Collections.reverse(notices);
		Collections.sort(noticeUser, new Comparator<Tb_noticeUser>() {
			public int compare(Tb_noticeUser o1, Tb_noticeUser o2) {
				return (int) (o1.getCreated().getTime() - o2.getCreated().getTime());
			}
		});
		// 按时间排序
		for (int i = noticeUser.size() - 1; i >= 0; i--) {
			notice = noticeMapper.selectByPrimaryKey(noticeUser.get(i).getNoticeId());
			notice.setIs_read(noticeUser.get(i).getIsRead());
			notices.add(notice);
		}

		return notices;
	}

	/**
	 * 根据通知id删除该通知消息下的用户消息
	 */
	public int deleteNoticeUserByNoticeId(Integer noticeId) {
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
		criteria.andNoticeIdEqualTo(noticeId);
		criteria.andDeletedEqualTo(false);
		List<Tb_noticeUser> noticeUsers = noticeUserMapper.selectByExample(condition);
		Tb_noticeUser noticeUser = null;
		int row = 0;
		for (int i = 0; i < noticeUsers.size(); i++) {
			noticeUser = noticeUsers.get(i);
			noticeUser.setDeleted(true);
			noticeUserMapper.updateByPrimaryKeySelective(noticeUser);
			row++;
		}
		return row;

	}

	/**
	 * 通告用户id通知id删除该用户的某条消息
	 */
	public int deleteNoticeByUserId(In_Read_Date date) {
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
		criteria.andNoticeIdEqualTo(date.getNoticeId());
		criteria.andDeletedEqualTo(false);
		criteria.andUserIdEqualTo(date.getUserId());
		List<Tb_noticeUser> noticeUsers = noticeUserMapper.selectByExample(condition);
		Tb_noticeUser noticeUser = noticeUsers.get(0);
		noticeUser.setDeleted(true);
		return noticeUserMapper.updateByPrimaryKeySelective(noticeUser);
	}

	/**
	 * 通告通知id删除通知信息
	 */
	public int deleteNoticeById(Integer id) {
		Tb_notice notice = noticeMapper.selectByPrimaryKey(id);
		notice.setDeleted(true);
		return noticeMapper.updateByPrimaryKeySelective(notice);
	}

	/**
	 * 设置消息已读
	 */
	public int setNoticeRead(Integer userId, Integer noticeId) {
		Tb_noticeUserExample condition = new Tb_noticeUserExample();
		Tb_noticeUserExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andUserIdEqualTo(userId);
		criteria.andNoticeIdEqualTo(noticeId);
		criteria.andDeletedEqualTo(false);
		List<Tb_noticeUser> noticeUser = noticeUserMapper.selectByExample(condition);
		Tb_noticeUser user = noticeUser.get(0);
		user.setIsRead((byte) 1);
		return noticeUserMapper.updateByPrimaryKeySelective(user);
	}
}
