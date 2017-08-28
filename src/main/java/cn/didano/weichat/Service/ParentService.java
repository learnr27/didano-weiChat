package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.didano.weichat.dao.Tb_school_parentMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Tb_school_parent;
import cn.didano.weichat.model.Tb_school_parentExample;

@Service
public class ParentService {
	@Autowired
	private Tb_school_parentMapper tb_school_parentMapper;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Tb_school_parent> selectAll() {
		Tb_school_parentExample condition = new Tb_school_parentExample();
		Tb_school_parentExample.Criteria criteria = condition.createCriteria();
		return tb_school_parentMapper.selectByExample(condition);
	}

	/**
	 * 查询集合
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<Tb_school_parent> selectAll(int page,int size) {
		PageHelper.startPage(page, size);
		Tb_school_parentExample condition = new Tb_school_parentExample();
		return new PageInfo<Tb_school_parent>(tb_school_parentMapper.selectByExample(condition));
	}

	/**
	 * 查询单条
	 * @param id
	 * @return
	 */
	public Tb_school_parent selectByPrimaryKey(Integer id) {
		return tb_school_parentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 插入
	 * @param record
	 * @return 有值，id ,否则返回-1
	 */
	public int insertSelective(Tb_school_parent record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return tb_school_parentMapper.insertSelective(record);
	}

	/**
	 * 删除
	 * @param id
	 * @return 删除行数
	 */
	public int deleteByPrimaryKey(int id) {
		if (id < 0)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_LESS_1); 
		return tb_school_parentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 更新
	 * @param record
	 * @return 更新行数
	 */
	public int updateByPrimaryKey(Tb_school_parent record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return tb_school_parentMapper.updateByPrimaryKey(record);
	}

}
