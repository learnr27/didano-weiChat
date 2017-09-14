package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.constant.DeletedType;
import cn.didano.weichat.constant.StatusType;
import cn.didano.weichat.dao.Tb_hobbyMapper;
import cn.didano.weichat.model.Tb_hobby;
import cn.didano.weichat.model.Tb_hobbyExample;

@Service
public class HobbyService {

	@Autowired
	private Tb_hobbyMapper hobbyMapper;
	
	
	/**
	 * 查找所有爱好
	 */
	public List<Tb_hobby> getAllHobby(){
		Tb_hobbyExample condition = new Tb_hobbyExample();
		Tb_hobbyExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		return hobbyMapper.selectByExample(condition);
	}
}
