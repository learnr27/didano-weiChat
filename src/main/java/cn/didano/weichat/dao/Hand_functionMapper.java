package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Tb_function;

public interface Hand_functionMapper {

	List<Tb_function> findAllFunctions();

	List<Tb_function> findFunctionsByUserId(Integer id);

	List<Tb_function> findAreaItems(String parentId);

	
	
	
}
