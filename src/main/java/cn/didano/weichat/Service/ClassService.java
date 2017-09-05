package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.didano.weichat.constant.DeletedType;
import cn.didano.weichat.dao.Tb_classMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Tb_class;
import cn.didano.weichat.model.Tb_classExample;

/**
 * 班级服务
 * @author stephen
 */
@Service
public class ClassService {
	@Autowired
	private Tb_classMapper classMapper;

	
	/**
	 * 查找一个学校里的id name对应
	 */
	public List<Tb_class> findAll(Integer id){
		Tb_classExample condition = new Tb_classExample();
		Tb_classExample.Criteria criteria = condition.createCriteria();
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andSchoolIdEqualTo(id);
		return classMapper.selectByExample(condition);
	}
	/**
	 * 通过班级id查询所属学校
	 */
	public Tb_class selectById(Integer id){
	
		return classMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Tb_class> selectAll() {
		Tb_classExample condition = new Tb_classExample();
		return classMapper.selectByExample(condition);
	}
	/**
	 * 查询一个学校所有班级
	 * 
	 * @return
	 */
	public List<Tb_class> selectSchoolAllClass(Integer schoolId) {
		Tb_classExample condition = new Tb_classExample();
		Tb_classExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andSchoolIdEqualTo(schoolId);
		return classMapper.selectByExample(condition);
	}
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Tb_class> selectAll4no_deleted() {
		Tb_classExample condition = new Tb_classExample();
		Tb_classExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		return classMapper.selectByExample(condition);
	}
	
	
	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<Tb_class> selectAllBySchool(int schoolId) {
		Tb_classExample condition = new Tb_classExample();
		Tb_classExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andSchoolIdEqualTo(schoolId);
		return classMapper.selectByExample(condition);
	}
	

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public String selectNameByPrimaryKey(int classId) {
		if(classId<0)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_LESS_1);
		return classMapper.selectByPrimaryKey(classId).getTitle();
	}
	
	/**
	 * 查询集合
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Tb_class> selectAll(int page, int size) {
		PageHelper.startPage(page, size);
		Tb_classExample condition = new Tb_classExample();
		Tb_classExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		return new PageInfo<Tb_class>(classMapper.selectByExample(condition)).getList();
	}

}
