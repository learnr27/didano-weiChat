package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_mailBoxMapper;
import cn.didano.weichat.model.Tb_mailBox;
import cn.didano.weichat.model.Tb_staff;

@Service
public class MailBoxService {

	@Autowired
	private Tb_mailBoxMapper mailBoxMapper;
	/**
	 * 写信
	 */
	public int writeMail(Tb_mailBox mailBox){
		return mailBoxMapper.insertSelective(mailBox);
	}
	/**
	 * 通过家长id查询学校所有园长
	 */
	public List<Tb_staff> selectBossByParentId(Integer userId){
		return mailBoxMapper.selectBossByParentId(userId);
	}
}
