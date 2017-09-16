package cn.didano.weichat.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.RoleService;
import cn.didano.weichat.Service.UserService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.constant.RoleType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Hand_userRoleRel;
import cn.didano.weichat.json.In_ParentSearchUserid;
import cn.didano.weichat.json.In_StaffSearchUserid;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.Out_Student_Search;
import cn.didano.weichat.model.Hand_RoleSelect;
import cn.didano.weichat.model.Hand_Role_Weichat;
import cn.didano.weichat.model.Hand_staff;
import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "微信用户登录控制服务", tags = "用户登录控制服务，对微信用户进行初始化权限控制")
@RestController
@RequestMapping(value = "/base/login")
public class LoginController {

	static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	/*
	 * 获取登录用户的信息,获取成功返回当前用户的角色列表;
	 */
	@ApiOperation(value = "用户登录,加载当前用户的角色列表", notes = "用户登录,加载当前用户的角色列表")
	@GetMapping(value = "/getRoleSelect/{openid}")
	@ResponseBody
	public Out<ArrayList<Hand_RoleSelect>> getRoleSelect(
			@ApiParam(value = "用户登录", required = true) @PathVariable String openid, HttpServletRequest request) {
		logger.info("访问 LoginControler: getRoleSelect");
		Out<ArrayList<Hand_RoleSelect>> back = new Out<ArrayList<Hand_RoleSelect>>();
		ArrayList<Hand_RoleSelect> hand_RoleSelects = new ArrayList<Hand_RoleSelect>();
		try {
			String openId = null;
			openId = request.getParameter("openid");
			if (openId == null) {
				openId = openid;
			}
			logger.info("openId数据为:  " + openId);
			/*
			 *  public final static int AGENT_ADMINISTRATOR = 25;		//代理商管理员;
				public final static int PRODUCT_ADMINISTRATOR = 26;		//生产管理员;
				public final static int SCHOOL_ADMINISTRATOR = 27;		//学校管理员;
				public final static int ORG_ADMINISTRATOR = 28;			//组织管理员;
				public final static int SUPER_ADMINISTRATOR = 29;		//超级管理员;
			 */
			//目前有11种角色类型,依次为家长,园长,老师,医生,勤务,行政,(超级管理员,组织管理员,学校管理员,生产管理员,代理商管理员);
			Hand_RoleSelect hand_RoleSelect1 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect2 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect3 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect4 = new Hand_RoleSelect(); 
			Hand_RoleSelect hand_RoleSelect5 = new Hand_RoleSelect(); 
			Hand_RoleSelect hand_RoleSelect6 = new Hand_RoleSelect();
			/*
			Hand_RoleSelect hand_RoleSelect7 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect8 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect9 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect10 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect11 = new Hand_RoleSelect();
			*/
			 
			hand_RoleSelect1.setRoleType(RoleType.PARENT);
			hand_RoleSelect1.setRoleName(RoleType.PARENT_NAME);

			hand_RoleSelect2.setRoleType(RoleType.PRINCIPAL);
			hand_RoleSelect2.setRoleName(RoleType.PRINCIPAL_NAME);

			hand_RoleSelect3.setRoleType(RoleType.TEACHER);
			hand_RoleSelect3.setRoleName(RoleType.TEACHER_NAME);
			//医生
			/*
			 * hand_RoleSelect4.setRoleType(RoleType.TEACHER);
			 * hand_RoleSelect4.setRoleName(RoleType.TEACHER_NAME);
			*/
			hand_RoleSelect4.setRoleType(RoleType.DOCTOR);
			hand_RoleSelect4.setRoleName(RoleType.DOCTOR_NAME);
			
			hand_RoleSelect5.setRoleType(RoleType.SERVICE);
			hand_RoleSelect5.setRoleName(RoleType.SERVICE_NAME);
			
			hand_RoleSelect6.setRoleType(RoleType.ASSISTANT);
			hand_RoleSelect6.setRoleName(RoleType.ASSISTANT_NAME);
			
			/*hand_RoleSelect7.setRoleType(RoleType.SUPER_ADMINISTRATOR);
			hand_RoleSelect7.setRoleName(RoleType.SUPER_ADMINISTRATOR_NAME);
			
			hand_RoleSelect8.setRoleType(RoleType.ORG_ADMINISTRATOR);
			hand_RoleSelect8.setRoleName(RoleType.ORG_ADMINISTRATOR_NAME);
			
			hand_RoleSelect9.setRoleType(RoleType.SCHOOL_ADMINISTRATOR);
			hand_RoleSelect9.setRoleName(RoleType.SCHOOL_ADMINISTRATOR_NAME);
			
			hand_RoleSelect10.setRoleType(RoleType.PRODUCT_ADMINISTRATOR);
			hand_RoleSelect10.setRoleName(RoleType.PRODUCT_ADMINISTRATOR_NAME);
			
			hand_RoleSelect11.setRoleType(RoleType.AGENT_ADMINISTRATOR);
			hand_RoleSelect11.setRoleName(RoleType.AGENT_ADMINISTRATOR_NAME);*/
			 

			// 家长
			List<Out_Student_Search> studenList = userService.getStudentListByOpenid(openId);
			for (Out_Student_Search student : studenList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setRoleType(RoleType.PARENT);
				hand_Role_Weichat.setRoleName(RoleType.PARENT_NAME);
				hand_Role_Weichat.setId(student.getId());
				hand_Role_Weichat.setParentId(student.getParentId());
				hand_Role_Weichat.setSchoolId(student.getSchoolId());
				hand_Role_Weichat.setSchoolName(student.getSchooltitle());
				hand_Role_Weichat.setStudentId(student.getStudentId());
				hand_Role_Weichat.setStudentName(student.getName());
				hand_Role_Weichat.setClassId(student.getClassId());
				hand_Role_Weichat.setClassName(student.getClassName());
				hand_RoleSelect1.getList().add(hand_Role_Weichat);
			}
			// 园长
			List<Hand_staff> schoolList = userService.getSchoolListByOpenid(openId);
			for (Hand_staff school : schoolList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setRoleType(RoleType.PRINCIPAL);
				hand_Role_Weichat.setRoleName(RoleType.PRINCIPAL_NAME);
				hand_Role_Weichat.setId(school.getId());
				hand_Role_Weichat.setStaffId(school.getStaffId());

				hand_Role_Weichat.setSchoolId(school.getSchoolId());
				hand_Role_Weichat.setSchoolName(school.getSchoolName());
				hand_RoleSelect2.getList().add(hand_Role_Weichat);
			}
			
			// 老师
			List<Hand_staff> teacherList = userService.getTeacherByOpenid(openId);
			List<Hand_staff> teacherInSchoolList = null;
			for (Hand_staff staff : teacherList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setRoleType(RoleType.TEACHER);
				hand_Role_Weichat.setRoleName(RoleType.TEACHER_NAME);
				hand_Role_Weichat.setId(staff.getId());
				hand_Role_Weichat.setStaffId(staff.getStaffId());
				hand_Role_Weichat.setClassId(staff.getClassId());
				hand_Role_Weichat.setClassName(staff.getClassName());
				hand_Role_Weichat.setSchoolId(staff.getSchoolId());
				hand_Role_Weichat.setSchoolName(staff.getSchoolName());
				hand_RoleSelect3.getList().add(hand_Role_Weichat);
			}
			
			// 医生
			List<Hand_staff> doctorList = userService.getDoctorByOpenid(openId);
			for (Hand_staff staff : doctorList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setRoleType(RoleType.DOCTOR);
				hand_Role_Weichat.setRoleName(RoleType.DOCTOR_NAME);
				hand_Role_Weichat.setId(staff.getId());
				hand_Role_Weichat.setStaffId(staff.getStaffId());
				hand_Role_Weichat.setClassId(staff.getClassId());
				hand_Role_Weichat.setClassName(staff.getClassName());
				hand_Role_Weichat.setSchoolId(staff.getSchoolId());
				hand_Role_Weichat.setSchoolName(staff.getSchoolName());
				hand_RoleSelect4.getList().add(hand_Role_Weichat);
			}
			
			// 勤务
			List<Hand_staff> serviceList = userService.getServiceByOpenid(openId);
			for (Hand_staff staff : serviceList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setRoleType(RoleType.SERVICE);
				hand_Role_Weichat.setRoleName(RoleType.SERVICE_NAME);
				hand_Role_Weichat.setId(staff.getId());
				hand_Role_Weichat.setStaffId(staff.getStaffId());
				hand_Role_Weichat.setClassId(staff.getClassId());
				hand_Role_Weichat.setClassName(staff.getClassName());
				hand_Role_Weichat.setSchoolId(staff.getSchoolId());
				hand_Role_Weichat.setSchoolName(staff.getSchoolName());
				hand_RoleSelect5.getList().add(hand_Role_Weichat);
			}
			
			// 行政
			List<Hand_staff> assistantList = userService.getAssistantByOpenid(openId);
			for (Hand_staff staff : assistantList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setRoleType(RoleType.ASSISTANT);
				hand_Role_Weichat.setRoleName(RoleType.ASSISTANT_NAME);
				hand_Role_Weichat.setId(staff.getId());
				hand_Role_Weichat.setStaffId(staff.getStaffId());
				hand_Role_Weichat.setClassId(staff.getClassId());
				hand_Role_Weichat.setClassName(staff.getClassName());
				hand_Role_Weichat.setSchoolId(staff.getSchoolId());
				hand_Role_Weichat.setSchoolName(staff.getSchoolName());
				hand_RoleSelect6.getList().add(hand_Role_Weichat);
			}
			
			// 医生,勤务,行政暂时没有做;
			hand_RoleSelect1.setNum(hand_RoleSelect1.getList().size());
			hand_RoleSelect2.setNum(hand_RoleSelect2.getList().size());
			hand_RoleSelect3.setNum(hand_RoleSelect3.getList().size());
			hand_RoleSelect4.setNum(hand_RoleSelect4.getList().size());
			hand_RoleSelect5.setNum(hand_RoleSelect5.getList().size());
			hand_RoleSelect6.setNum(hand_RoleSelect6.getList().size());
			
			 /* hand_RoleSelect5.setNum(hand_RoleSelect5.getList().size());
			 * hand_RoleSelect6.setNum(hand_RoleSelect6.getList().size());
			 */

			if (hand_RoleSelect1.getNum() > 0) {
				hand_RoleSelects.add(hand_RoleSelect1);
			}
			if (hand_RoleSelect2.getNum() > 0) {
				hand_RoleSelects.add(hand_RoleSelect2);
			}
			if (hand_RoleSelect3.getNum() > 0) {
				hand_RoleSelects.add(hand_RoleSelect3);
			}else {
				teacherInSchoolList = userService.getTeacherInSchoolByOpenid(openId);
			}
			if (hand_RoleSelect4.getNum() > 0) {
				hand_RoleSelects.add(hand_RoleSelect4);
			}
			if (hand_RoleSelect5.getNum() > 0) {
				hand_RoleSelects.add(hand_RoleSelect5);
			}
			if (hand_RoleSelect6.getNum() > 0) {
				hand_RoleSelects.add(hand_RoleSelect6);
			}
			/*
			 * if (hand_RoleSelect4.getNum() > 0) { hand_RoleSelects.add(hand_RoleSelect4);
			 * } if (hand_RoleSelect5.getNum() > 0) {
			 * hand_RoleSelects.add(hand_RoleSelect5); } if (hand_RoleSelect6.getNum() > 0)
			 * { hand_RoleSelects.add(hand_RoleSelect6); }
			 */
			if (teacherInSchoolList != null && teacherInSchoolList.size() > 0 ) {
				back.setBackTypeWithLog(hand_RoleSelects, BackType.SUCCESS_SEARCH_TEACHER_NO_CLASS);
			}else {
				back.setBackTypeWithLog(hand_RoleSelects, BackType.SUCCESS_SEARCH_NORMAL);
			}

		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception e) {
			logger.warn(e.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, e.getMessage());
		}
		return back;
	}

	/*
	 * 
	 */
	/*
	 * @ApiOperation(value = "根据用户角色id选择角色", notes = "选择一个角色后,禁用其它的角色")
	 * 
	 * @GetMapping(value = "getUseridByStaffid/{openid}/{staffid}/")
	 * 
	 * @ResponseBody public Out<Integer> getUseridByStaffid(@PathVariable String
	 * openid,@PathVariable Integer staffid) { Out<Integer> back = new Out<>(); try
	 * { Integer userid = userService.getUseridByStaffid(openid,staffid);
	 * back.setBackTypeWithLog(userid, BackType.SUCCESS_SEARCH_NORMAL); } catch
	 * (Exception e) {
	 * logger.info("openid: "+openid+", staffid: "+staffid+"查询失败!!");
	 * back.setBackType(BackType.FAIL_SEARCH_NORMAL, e.getMessage()); } return back;
	 * }
	 */
	
	/*
	 * 根据用户openid,staffid 查询userid;
	 */
	@ApiOperation(value = "根据用户openid,staffid查询userid", notes = "根据用户openid,staffid查询userid")
	@PostMapping(value = "/getUseridByStaff")
	@ResponseBody
	public Out<List<Tb_user>> getUseridByStaffid(@ApiParam(value = "用户登录", required = true) @RequestBody List<In_StaffSearchUserid> inPara) {
		Out<List<Tb_user>> back = new Out<>();
		try {
			List<Tb_user> userList = userService.getUseridByStaffParam(inPara);
			back.setBackTypeWithLog(userList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (Exception e) {
			back.setBackType(BackType.FAIL_SEARCH_NORMAL, e.getMessage());
		}
		return back;
	}
	
	/*
	 * 根据用户openid,parentid 查询userid;
	 */
	@ApiOperation(value = "根据用户openid,parentid查询userid", notes = "根据用户openid,parentid查询userid")
	@PostMapping(value = "/getUseridByParent")
	@ResponseBody
	public Out<List<Tb_user>> getUseridByParentid(@ApiParam(value = "用户登录", required = true) @RequestBody List<In_ParentSearchUserid> inPara) {
		Out<List<Tb_user>> back = new Out<>();
		try {
			List<Tb_user> userList = userService.getUseridByParentParam(inPara);
			back.setBackTypeWithLog(userList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (Exception e) {
			back.setBackType(BackType.FAIL_SEARCH_NORMAL, e.getMessage());
		}
		return back;
	}

	/*
	 * 根据当前用户选择的角色选择相应的权限;
	 */
	@ApiOperation(value = "根据用户角色id选择角色", notes = "选择一个角色后,禁用其它的角色")
	@PostMapping(value = "userRoleSelected/{role_id}")
	@ResponseBody
	public Out<String> userRoleSelected(@PathVariable("role_id") Integer role_id) {
		logger.info("访问  LoginController:userRoleSelected,role_id=" + role_id);

		Out<String> back = new Out<>();
		Subject subject = SecurityUtils.getSubject();
		Tb_user user = (Tb_user) subject.getPrincipal();
		Integer userId = user.getId();
		logger.info("userId=" + userId + ",role_id=" + role_id);
		Integer rowNum1 = null;
		Integer rowNum2 = null;
		Set<Tb_role> set = null;
		Hand_userRoleRel data = new Hand_userRoleRel();
		data.setUserId(userId);
		List<Tb_role> list = roleService.findAllRolesByUserId(user.getId());
		System.out.println(list);
		for (Tb_role role : list) {
			System.out.println(role.getId());
			if (list.size() > 1) {
				try {
					if (role_id.equals(role.getId())) {
						data.setRoleId(role.getId());
						rowNum1 = userService.enableRoleStatusById(data);
						System.out.println("启用当前用户,name=" + role.getName());
						// user.getRoles().add(role);
					} else {
						data.setRoleId(role.getId());
						rowNum2 = userService.disableRoleStatusById(data);
						System.out.println("禁用当前用户,name=" + role.getName());
						// user.getRoles().remove(role);
					}
					// user.setRoles(set);
					// Set<Tb_role> roles = user.getRoles();
					// System.out.println(roles);
					if (rowNum1 > 0 && rowNum2 > 0) {
						back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "rowNum1=" + rowNum1, "rowNum2=" + rowNum2);
					} else {
						back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "rowNum1=" + rowNum1,
								"rowNum2=" + rowNum2);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} else if (list.size() == 1) {
				rowNum1 = userService.enableRoleStatusById(data);
				back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "rowNum1=" + rowNum1, "rowNum2=" + rowNum2);
			} else {
				back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "rowNum1=" + rowNum1, "rowNum2=" + rowNum2);
			}

		}

		return back;
	}

}
