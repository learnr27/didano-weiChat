package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.json.Hand_userRoleRel;
import cn.didano.weichat.json.Out_Student_Search;
import cn.didano.weichat.model.Hand_staff;
import cn.didano.weichat.model.Tb_school;
import cn.didano.weichat.model.Tb_schoolParent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_user;
import cn.didano.weichat.util.Hand_StudentParent;

public interface Hand_userMapper {
	
	List<Tb_user> selectUserByOpenid(String openid);
	
	List<Tb_user> getUsersByopenId(String openid);

	int enableRoleStatusById(Hand_userRoleRel data);
	
	int disableRoleStatusById(Hand_userRoleRel data);

	List<Tb_user> findAllUsers();

	List<Out_Student_Search> getStudentListByMobile(String parentPhone);

	List<Tb_school> getSchoolListByMobile(String mobile);

	String getRelationByRelationId(Integer relation_id);

	Hand_staff getTeacherByMobile(String mobile);

	String getMobileByOpenId(String openId);

	List<Tb_user> getUserListFromOthers();

	List<Tb_staff> getStaffByMobileFromOthers(String mobile);

	List<Tb_schoolParent> getParentListByMobile(String mobile);

	List<Out_Student_Search> getStudentListByOpenid(String openId);

	List<Hand_staff> getSchoolListByOpenid(String openId);

	List<Hand_staff> getTeacherByOpenid(String openId);

	List<Hand_StudentParent> getStudentParentListByMobile(String mobile);

	Integer deleteDatafrom(Integer row);
	
}
