package cn.didano.weichat.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.Controller.LoginController;
import cn.didano.weichat.dao.Hand_userMapper;
import cn.didano.weichat.dao.Tb_userMapper;
import cn.didano.weichat.json.Hand_userRoleRel;
import cn.didano.weichat.json.In_ParentSearchUserid;
import cn.didano.weichat.json.In_StaffSearchUserid;
import cn.didano.weichat.json.Out_Student_Search;
import cn.didano.weichat.model.Hand_staff;
import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_school;
import cn.didano.weichat.model.Tb_school_parent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_user;
import cn.didano.weichat.util.Hand_StudentParent;

@Service
public class UserService {
	
	static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private Hand_userMapper handUserMapper;
	@Autowired
	private Tb_userMapper userMapper;
	
	
	public List<Tb_user> selectUserByOpenid(String openid) {
		List<Tb_user> users = handUserMapper.selectUserByOpenid(openid);
//		List<Tb_user> users = handUserMapper.selectUserByOpenid(openid);
		System.out.println(users);
		return users;
		
		/*UserExample condition = new UserExample();
		UserExample.Criteria criteria = condition.createCriteria();
		   //对于已经deleted=1的不显示 禁用不显示
		 	criteria.andUsernameEqualTo(username);
		 	return userMapper.selectByExample(condition).get(0);*/
		
		/*String id = "4028821e5b7a6947015b7a6ebacf0000";
		return userMapper.selectByPrimaryKey(id);*/
	}
	
	
	/*public List<Tb_user> selectUserByOpenid(String openid,int type) {
		List<Tb_user> users = handUserMapper.selectUserByOpenid(openid,type);
		System.out.println(users);
		return users;
		
		UserExample condition = new UserExample();
		UserExample.Criteria criteria = condition.createCriteria();
		   //对于已经deleted=1的不显示 禁用不显示
		 	criteria.andUsernameEqualTo(username);
		 	return userMapper.selectByExample(condition).get(0);
		
		String id = "4028821e5b7a6947015b7a6ebacf0000";
		return userMapper.selectByPrimaryKey(id);
	}*/
	
	public int enableRoleStatusById(Hand_userRoleRel data) {
		int rowNum = handUserMapper.enableRoleStatusById(data);
		System.out.println(rowNum);
		return rowNum;		
	}
	
	public int disableRoleStatusById(Hand_userRoleRel data) {
		int rowNum = handUserMapper.disableRoleStatusById(data);
		System.out.println(rowNum);
		return rowNum;		
	}
	
	public List<Tb_user> findAllUsers() {
		return handUserMapper.findAllUsers();		
	}


	public int updateByPrimaryKeySelective(Tb_user user) {
		int rowNum = userMapper.updateByPrimaryKeySelective(user);
		return rowNum;
	}

	
	public List<Out_Student_Search> getStudentListByMobile(String parentPhone) {
		List<Out_Student_Search> list = handUserMapper.getStudentListByPhone(parentPhone);
		System.out.println(list);
		return list;
	}


	public List<Tb_school> getSchoolListByMobile(String mobile) {
		List<Tb_school> list = handUserMapper.getSchoolListByMobile(mobile);
		return list;
	}


	public String getRelationByRelationId(Integer relation_id) {
		String relation = handUserMapper.getRelationByRelationId(relation_id);
		return relation;
	}


	public Hand_staff getTeacherByMobile(String phone) {
		Hand_staff teacher = handUserMapper.getTeacherByPhone(phone);
		return teacher;
	}


	public Tb_role getUserActiveRole(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public String getMobileByOpenId(String openId) {
		String mobile = handUserMapper.getMobileByOpenId(openId);
		return mobile;
	}


	public List<Tb_user> getUserListFromOthers() {
		List<Tb_user> userList = null;
		userList = handUserMapper.getUserListFromOthers();
		System.out.println(userList);
		return userList;
	}


	public List<Tb_staff> getStaffByPhoneFromOthers(String phone) {
		List<Tb_staff> StaffList = handUserMapper.getStaffByPhoneFromOthers(phone);
		return StaffList;
	}


	public void save(Tb_user tb_user) {
		try {
			userMapper.insert(tb_user);
			logger.info("插入数据成功!!");
		} catch (Exception e) {
			// TODO: handle exception
			logger.warn("数据插入失败,tb_user"+tb_user.getOpenid()+" ... "+tb_user.getSchoolParentId()  +" ... "+tb_user.getStaffId());
		}
	}


	/*public List<Tb_schoolParent> getParentListByMobile(String mobile) {
		List<Tb_schoolParent> parentList = handUserMapper.getParentListByMobile(mobile);
		return parentList;
	}*/


	public List<Out_Student_Search> getStudentListByOpenid(String openId) {
		List<Out_Student_Search> list = handUserMapper.getStudentListByOpenid(openId);
		return list;
	}


	public List<Hand_staff> getSchoolListByOpenid(String openId) {
		List<Hand_staff> list = handUserMapper.getSchoolListByOpenid(openId);
		return list;
	}


	public List<Hand_staff> getTeacherByOpenid(String openId) {
		List<Hand_staff> teacherList = handUserMapper.getTeacherByOpenid(openId);
		return teacherList;
	}


	public List<Hand_StudentParent> getStudentParentListByPhone(String phone) {
		List<Hand_StudentParent> list = handUserMapper.getStudentParentListByPhone(phone);
		return list;
	}


	public Integer deleteDatafrom(Integer row) {
		Integer rowNum = handUserMapper.deleteDatafrom(row);
		return rowNum;
	}


	public List<Tb_user> getUseridByStaffParam(List<In_StaffSearchUserid> inPara) {
		List<Tb_user> userList = new ArrayList<>();
		if (inPara.size() > 0 && inPara != null) {
			for (In_StaffSearchUserid staff : inPara) {
				if (staff.getOpenid() != null && staff.getStaffid() != null) {
					Tb_user user = handUserMapper.getUseridByStaffid(staff);
					userList.add(user);
				}
			}
		}
		return userList;
	}


	public List<Tb_user> getUseridByParentParam(List<In_ParentSearchUserid> inPara) {
		List<Tb_user> userList = new ArrayList<>();
		if (inPara.size() > 0 && inPara != null) {
			for (In_ParentSearchUserid parent : inPara) {
				if (parent.getOpenid() != null && parent.getParentid() != null) {
					Tb_user user = handUserMapper.getUseridByParentid(parent);
					userList.add(user);
				}
			}
		}
		return userList;
	}

	
	public List<Tb_student_parent> getDuplicateStudentParentData() {
		return handUserMapper.getDuplicateStudentParentData();
	}
	
	public List<Tb_school_parent> getDuplicateParentData() {
		return handUserMapper.getDuplicateParentData();
	}


	public List<Hand_staff> getDoctorByOpenid(String openId) {
		List<Hand_staff> doctorList = handUserMapper.getDoctorByOpenid(openId);
		return doctorList;
	}


	public List<Hand_staff> getServiceByOpenid(String openId) {
		List<Hand_staff> serviceList = handUserMapper.getServiceByOpenid(openId);
		return serviceList;
	}


	public List<Hand_staff> getAssistantByOpenid(String openId) {
		List<Hand_staff> assistantList = handUserMapper.getAssistantByOpenid(openId);
		return assistantList;
	}
	
}
