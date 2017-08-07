package cn.didano.weichat.Service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.Controller.LoginController;
import cn.didano.weichat.dao.Hand_userMapper;
import cn.didano.weichat.dao.Tb_userMapper;
import cn.didano.weichat.json.Out_Student_Search;
import cn.didano.weichat.model.Hand_teacher;
import cn.didano.weichat.model.Hand_userRoleRel;
import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_school;
import cn.didano.weichat.model.Tb_schoolParent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_user;

@Service
public class UserService {
	
	static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private Hand_userMapper handUserMapper;
	@Autowired
	private Tb_userMapper userMapper;
	
	
	public Tb_user selectUserByOpenid(String openid) {
		Tb_user user = handUserMapper.selectUserByOpenid(openid);
		System.out.println(user);
		return user;
		
		/*UserExample condition = new UserExample();
		UserExample.Criteria criteria = condition.createCriteria();
		   //对于已经deleted=1的不显示 禁用不显示
		 	criteria.andUsernameEqualTo(username);
		 	return userMapper.selectByExample(condition).get(0);*/
		
		/*String id = "4028821e5b7a6947015b7a6ebacf0000";
		return userMapper.selectByPrimaryKey(id);*/
	}
	
	
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
		List<Out_Student_Search> list = handUserMapper.getStudentListByMobile(parentPhone);
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


	public Hand_teacher getTeacherByMobile(String mobile) {
		Hand_teacher teacher = handUserMapper.getTeacherByMobile(mobile);
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


	public List<Tb_staff> getStaffByMobileFromOthers(String mobile) {
		List<Tb_staff> StaffList = handUserMapper.getStaffByMobileFromOthers(mobile);
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


	public List<Tb_schoolParent> getParentListByMobile(String mobile) {
		List<Tb_schoolParent> parentList = handUserMapper.getParentListByMobile(mobile);
		return parentList;
	}
	

	
}
