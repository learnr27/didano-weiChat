package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.didano.weichat.dao.Tb_student_parentMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_student_parentExample;

@Service
public class StudentParentService {
	@Autowired
	private Tb_student_parentMapper tb_student_parentMapper;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Tb_student_parent> selectAll() {
		Tb_student_parentExample condition = new Tb_student_parentExample();
		Tb_student_parentExample.Criteria criteria = condition.createCriteria();
		return tb_student_parentMapper.selectByExample(condition);
	}

	/**
	 * 根据学生id家长id查询家长
	 * @return
	 */
	public Tb_student_parent selectParentByid(Integer studentId,Integer parentId) {
		Tb_student_parentExample condition = new Tb_student_parentExample();
		Tb_student_parentExample.Criteria criteria = condition.createCriteria();
		criteria.andDeletedEqualTo(false);
		criteria.andStudentIdEqualTo(studentId);
		criteria.andParentIdEqualTo(parentId);
		if(tb_student_parentMapper.selectByExample(condition).size()!=0){
		return tb_student_parentMapper.selectByExample(condition).get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据学家长id查询学生id
	 * @return
	 */
	public Tb_student_parent selectStudentByParentid(Integer parentId) {
		Tb_student_parentExample condition = new Tb_student_parentExample();
		Tb_student_parentExample.Criteria criteria = condition.createCriteria();
		criteria.andDeletedEqualTo(false);
		criteria.andParentIdEqualTo(parentId);
		if(tb_student_parentMapper.selectByExample(condition).size()!=0){
		return tb_student_parentMapper.selectByExample(condition).get(0);
		}else{
			return null;
		}
	}
	/**
	 * 查询集合
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<Tb_student_parent> selectAll(int page,int size) {
		PageHelper.startPage(page, size);
		Tb_student_parentExample condition = new Tb_student_parentExample();
		return new PageInfo<Tb_student_parent>(tb_student_parentMapper.selectByExample(condition));
	}

	/**
	 * 查询单条
	 * @param id
	 * @return
	 */
	public Tb_student_parent selectByPrimaryKey(Integer id) {
		return tb_student_parentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 插入
	 * @param record
	 * @return 有值，id ,否则返回-1
	 */
	public int insertSelective(Tb_student_parent record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return tb_student_parentMapper.insertSelective(record);
	}

	/**
	 * 删除
	 * @param id
	 * @return 删除行数
	 */
	public int deleteByPrimaryKey(int id) {
		if (id < 0)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_LESS_1); 
		return tb_student_parentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 更新
	 * @param record
	 * @return 更新行数
	 */
	public int updateByPrimaryKey(Tb_student_parent record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return tb_student_parentMapper.updateByPrimaryKey(record);
	}

}
