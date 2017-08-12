package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Tb_function;
import cn.didano.weichat.model.Tb_role;

public interface Hand_roleMapper {

	List<Tb_role> selectRolesByUserId(Integer id);

	List<Tb_role> getAllRoles();

	Tb_role getActiveRoleByUserid(Integer userid);

	List<Tb_function> getFunctionsByRoletype(Integer roletype);
}