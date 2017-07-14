package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.constant.DeletedType;
import cn.didano.weichat.dao.Tb_dreamMapper;
import cn.didano.weichat.model.Tb_dream;
import cn.didano.weichat.model.Tb_dreamExample;

@Service
public class DreamService {

	@Autowired
	private Tb_dreamMapper dreamMapper;
	
	
	/**
	 * 查找所有爱好
	 */
	public List<Tb_dream> getAllDreams(){
		Tb_dreamExample condition = new Tb_dreamExample();
		Tb_dreamExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		return dreamMapper.selectByExample(condition);
	}
}
