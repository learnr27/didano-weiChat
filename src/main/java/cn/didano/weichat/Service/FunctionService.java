package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Hand_functionMapper;
import cn.didano.weichat.dao.Tb_functionMapper;
import cn.didano.weichat.model.Tb_function;

@Service
public class FunctionService {

	@Autowired
	private Tb_functionMapper functionMapper;
	@Autowired
	private Hand_functionMapper handFunctionMapper;
	
	
	public List<Tb_function> findAllFunctions() {
		return handFunctionMapper.findAllFunctions();
	}
	
	public List<Tb_function> findFunctionsByUserId(Integer id) {
		return handFunctionMapper.findFunctionsByUserId(id);
	}

	public List<Tb_function> findAreaItems(Integer parentId) {
		List<Tb_function> list = handFunctionMapper.findAreaItems(parentId);
		return list;
	}
	
	
	
}
