package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Hand_class;

public interface Hand_classMapper {
	List<Hand_class> findClassName(Hand_class hc);
	List<Hand_class> findSchoolAll(String openid);
	Hand_class findScholById(Integer staff_id);
	
	Hand_class findClass(Integer class_id);
	
	List<Hand_class> findClassAll(Integer schoolId);
	
	int updateClass(Hand_class classInfo);
	
	int insertCalss(Hand_class classInfo);
	
	List<Hand_class> findParent(Integer class_id);
	
	int deletedClass(Integer class_id);
	
	int deletedStudent(Integer class_id);
	
	int deletedParent(Integer class_id);
	
	int deletedStudentParent(Integer class_id);
	
	int findClassByStudent(Integer class_id);
}