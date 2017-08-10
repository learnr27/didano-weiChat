package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_studentMapper;
import cn.didano.weichat.exception.DBExceptionEnums;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.model.Hand_parentAndStudentId;
import cn.didano.weichat.model.Tb_student;

@Service
public class StudentService {

	@Autowired
	private Tb_studentMapper studentMapper;
	
	
	/**
	 * 根据家长电话号码查找他的所有孩子
	 */
	public List<Tb_student> getStudentListByParentPhone(String parentPhone){
		return studentMapper.getStudentListByParentPhone(parentPhone);
	}
	
	/**
	 * 根据父母id学生ID查找
	 */
	public Tb_student getStudentByParentId(Hand_parentAndStudentId parentAndStudent){
		return studentMapper.getStudentByParentId(parentAndStudent);
	}
	
	/**
	 * 编辑学生信息
	 */
	public int updateStudentSetting(Tb_student record){
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return studentMapper.updateByPrimaryKeySelective(record);
	}
}
