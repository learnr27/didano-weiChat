package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Hand_videoAuthSchool;
import cn.didano.weichat.model.Tb_function;

public interface Hand_functionMapper {

	List<Tb_function> findAllFunctions();

	List<Tb_function> findFunctionsByUserId(Integer id);

	List<Tb_function> findAreaItems(Integer parentId);

	Hand_videoAuthSchool getStaffSchoolByStaffId(Integer staffId);

	Hand_videoAuthSchool getstudentSchoolByStudentId(Integer studentId);

	List<Hand_videoAuthSchool> getAuthSchools();

	
	
	
}
