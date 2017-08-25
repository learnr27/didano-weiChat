package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Hand_WholeStudentParents4PhoneBook;
import cn.didano.weichat.model.Hand_parent4mailList;
import cn.didano.weichat.model.Hand_staff4PhoneBook;
import cn.didano.weichat.model.Hand_staffTransit4PhoneBook;
import cn.didano.weichat.model.Hand_wholeStudent4PhoneBook;
import cn.didano.weichat.model.Hand_wholeStudentParent4PhoneBook;
import cn.didano.weichat.model.Tb_deleteParentDate;
import cn.didano.weichat.model.Tb_relation;
import cn.didano.weichat.model.Tb_schoolParent;
import cn.didano.weichat.model.Tb_staffData;
import cn.didano.weichat.model.Tb_student;
import cn.didano.weichat.model.Tb_student4List;
import cn.didano.weichat.model.Tb_studentData;
import cn.didano.weichat.model.Tb_student_parent;

public interface Hand_MailList_listMapper {

	List<Hand_WholeStudentParents4PhoneBook> findByTeacher(Integer id);
	List<Hand_WholeStudentParents4PhoneBook> findAll();
	Hand_WholeStudentParents4PhoneBook findById(Integer id);
	List<Hand_parent4mailList> findParentById(Integer id);
	List<Hand_WholeStudentParents4PhoneBook> findByClass(Integer id);
	List<Hand_staff4PhoneBook> findTeacherByClass(Integer id);
	List<Hand_staffTransit4PhoneBook> findTeacherByNameClass(Tb_staffData data2);
	Hand_staff4PhoneBook findBystaffbyId(Integer id);
	List<Hand_staff4PhoneBook> findteacherByschool(Integer id);
	List<Hand_WholeStudentParents4PhoneBook> findByschool(Integer id);
	List<Hand_wholeStudentParent4PhoneBook> findWholeStudentParentsByschool(Integer id);
	
	//杨 添加根据学生的ic_number进行查询
	Tb_student4List findStudentByIcNumber(Tb_student tb_student);
	Hand_wholeStudent4PhoneBook findParentByIcNumber(Tb_student tb_student);
	Hand_staff4PhoneBook selectSchoolBystaffId(Integer id);
	
	
	List<Hand_WholeStudentParents4PhoneBook> findByName(Tb_studentData data);
	List<Hand_WholeStudentParents4PhoneBook> findByNameClass(Tb_studentData data);
	List<Tb_relation> findrelation();
	Tb_relation findrelationById(Integer id);
	List<Hand_parent4mailList> findParentByPid(Tb_student_parent tsp);
	Tb_staffData findClassIdBySid(Integer id);
	Hand_staff4PhoneBook findStaffById(Integer id );
	int Update( Hand_WholeStudentParents4PhoneBook list); 
	int UpdateSchoolParent(Hand_parent4mailList parent);
	int UpdateStudentParent(Hand_parent4mailList parent);
	int delete(Integer id);
	int deleteSchool_parentBySid(Integer id);
	int deleteStudent_parentBySid(Integer id);
	int deleteSingleSchoolparentByid(Tb_deleteParentDate date);
	int deleteSingleStudentlparentByid(Tb_deleteParentDate date);
	List<Hand_parent4mailList> findParentByStudentId(Integer id);
	List<Tb_schoolParent> findParentByClass(Integer classId);
}
