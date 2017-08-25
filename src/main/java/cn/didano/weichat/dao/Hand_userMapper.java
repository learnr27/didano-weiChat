package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.json.Hand_userRoleRel;
import cn.didano.weichat.json.In_ParentSearchUserid;
import cn.didano.weichat.json.In_StaffSearchUserid;
import cn.didano.weichat.json.Out_Student_Search;
import cn.didano.weichat.model.Hand_staff;
import cn.didano.weichat.model.Tb_school;
import cn.didano.weichat.model.Tb_school_parent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_user;
import cn.didano.weichat.util.Hand_StudentParent;

public interface Hand_userMapper {
	
	List<Tb_user> selectUserByOpenid(String openid);
	
	List<Tb_user> getUsersByopenId(String openid);

	int enableRoleStatusById(Hand_userRoleRel data);
	
	int disableRoleStatusById(Hand_userRoleRel data);

	List<Tb_user> findAllUsers();

	List<Out_Student_Search> getStudentListByPhone(String parentPhone);

	List<Tb_school> getSchoolListByMobile(String phone);

	String getRelationByRelationId(Integer relation_id);

	Hand_staff getTeacherByPhone(String phone);

	String getMobileByOpenId(String openId);

	List<Tb_user> getUserListFromOthers();

	List<Tb_staff> getStaffByPhoneFromOthers(String phone);

	List<Tb_school_parent> getParentListByMobile(String mobile);

	List<Out_Student_Search> getStudentListByOpenid(String openId);

	List<Hand_staff> getSchoolListByOpenid(String openId);

	List<Hand_staff> getTeacherByOpenid(String openId);

	List<Hand_StudentParent> getStudentParentListByPhone(String phone);

	Integer deleteDatafrom(Integer row);

	Tb_user getUseridByStaffid(In_StaffSearchUserid staff);

	Tb_user getUseridByParentid(In_ParentSearchUserid parent);

	List<Tb_student_parent> getDuplicateStudentParentData();
	
	List<Tb_school_parent> getDuplicateParentData();
	
	
}
