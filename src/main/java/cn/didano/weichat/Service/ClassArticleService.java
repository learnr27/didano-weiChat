package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Hand_classMapper;
import cn.didano.weichat.model.Hand_class;

@Service
public class ClassArticleService {

	@Autowired
	private Hand_classMapper hand_classMapper;
	
	
	public List<Hand_class> findClassName(Hand_class hc){
		return hand_classMapper.findClassName(hc);
	}
	public List<Hand_class> findSchoolAll(String openid){
		return hand_classMapper.findSchoolAll(openid);
	}
	public Hand_class findScholById(Integer staff_id){
		return hand_classMapper.findScholById(staff_id);
	}
	public List<Hand_class> findClassAll(Integer schoolId){
		return hand_classMapper.findClassAll(schoolId);
	}
	
	public Hand_class findClass(Integer class_id){
		return hand_classMapper.findClass(class_id);
	}
	/**
	 * 对班级的信息进行修改
	 * 
	 */
	public int updateClass(Hand_class classInfo){
		return hand_classMapper.updateClass(classInfo);
	}
	/**
	 * 对班级的信息进行修改
	 * 
	 */
	public int insertCalss(Hand_class classInfo){
		return hand_classMapper.insertCalss(classInfo);
	}
	/**
	 * 删除学校家长
	 */
	public int deletedParent(Integer class_id){
		return hand_classMapper.deletedParent(class_id);
	}
	/**
	 * 删除学生家长
	 */
	public int deletedStudentParent(Integer class_id){
		return hand_classMapper.deletedStudentParent(class_id);
	}
	
	/**
	 * 删除班级
	 */
	public int deletedClass(Integer class_id){
		return hand_classMapper.deletedClass(class_id);
	}
	/**
	 * 删除班级
	 */
	public int deletedStudent(Integer class_id){
		return hand_classMapper.deletedStudent(class_id);
	}
	/**
	 * 查询要删除的班级下面有没有学生
	 */
	public int findClassByStudent(Integer class_id){
		return hand_classMapper.findClassByStudent(class_id);
	}
	
}
