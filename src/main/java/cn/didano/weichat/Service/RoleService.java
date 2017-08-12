package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Hand_roleMapper;
import cn.didano.weichat.dao.Tb_roleMapper;
import cn.didano.weichat.model.Tb_function;
import cn.didano.weichat.model.Tb_role;

@Service
public class RoleService {
	
	@Autowired
	private Tb_roleMapper roleMapper;
	@Autowired
	private Hand_roleMapper handRoleMapper;

	public List<Tb_role> getAllRoles() {
		List<Tb_role> list = handRoleMapper.getAllRoles();
		return list;
	}
	
	//删除角色(物理删除);
	public void deleteByPrimaryKey(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}
	
	//添加角色;
	public void insert(Tb_role record) {
		roleMapper.insert(record);
	}

	public void insertSelective(Tb_role record) {
		roleMapper.insertSelective(record);
	}
	
	//根据主键查询;
	public Tb_role selectByPrimaryKey(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	public void updateByPrimaryKeySelective(Tb_role record) {
		roleMapper.updateByPrimaryKeySelective(record);
	}
	
	public void updateByPrimaryKey(Tb_role record) {
		roleMapper.updateByPrimaryKey(record);
	}
	
	public List<Tb_role> findAllRolesByUserId(Integer id) {
		List<Tb_role> list = handRoleMapper.selectRolesByUserId(id);
		return list;
	}

	public Tb_role getActiveRoleByUserid(Integer userid) {
		Tb_role role = handRoleMapper.getActiveRoleByUserid(userid);
		return role;
	}

	public List<Tb_function> getFunctionsByRoletype(Integer roletype) {
		List<Tb_function> functionList = handRoleMapper.getFunctionsByRoletype(roletype);
		return functionList;
	}

	
	
	
}
