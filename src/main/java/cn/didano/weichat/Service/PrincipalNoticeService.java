package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_principal_noticeMapper;
import cn.didano.weichat.model.Tb_principal_notice;
import cn.didano.weichat.model.Tb_principal_noticeExample;

@Service
public class PrincipalNoticeService {

	@Autowired
	private Tb_principal_noticeMapper principalMapper;
	
	/**
	 * 插入园长通知
	 */
	public int insertSelective(Tb_principal_notice record){
		return principalMapper.insertSelective(record);
	}
	/**
	 * 通过id查找园长通知
	 */
	public List<Tb_principal_notice> selectById(Integer id){
		Tb_principal_noticeExample condition = new Tb_principal_noticeExample();
		Tb_principal_noticeExample.Criteria criteria = condition.createCriteria();
         //对于已经deleted=1的不显示 禁用不显示
 		criteria.andDeletedEqualTo(false);
 		criteria.andIdEqualTo(id);
 		return principalMapper.selectByExample(condition);
	}
}
