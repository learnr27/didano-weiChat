package cn.didano.weichat.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
import cn.didano.weichat.json.In_User_Login;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.Out_Student_Search;
import cn.didano.weichat.model.Hand_RoleSelect;
import cn.didano.weichat.model.Hand_Role_Weichat;
import cn.didano.weichat.model.Hand_staff;
import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_school;
import cn.didano.weichat.model.Tb_user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "微信用户登录控制服务", tags = "用户登录控制服务，对微信用户进行初始化权限控制")
@RestController
@RequestMapping(value = "/base/login/post/")
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
	@PostMapping(value = "getRoleSelect")
	@ResponseBody
	public Out<ArrayList<Hand_RoleSelect>> getRoleSelect(
			@ApiParam(value = "用户登录", required = true) @RequestBody In_User_Login inUser,HttpServletRequest request) {
		logger.info("访问 LoginControler: getRoleSelect");
		Out<ArrayList<Hand_RoleSelect>> back = new Out<ArrayList<Hand_RoleSelect>>();
		ArrayList<Hand_RoleSelect> hand_RoleSelects = new ArrayList<Hand_RoleSelect>();
		Tb_user user = null;
		try {
			// 获取账号信息
			user = new Tb_user();
			BeanUtils.copyProperties(user, inUser);
//			String openId = user.getOpenid();
			String openId = null;
			openId = request.getParameter("openid");
			if (openId == null ) {
				openId = user.getOpenid();
			}
			logger.info("openId数据为:  " + openId);
			String mobile = userService.getMobileByOpenId(openId);
			logger.info("mobile数据为:  " + mobile);
			boolean rememberMe = false;
			UsernamePasswordToken token = new UsernamePasswordToken(openId, mobile, rememberMe);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			user = (Tb_user) subject.getPrincipal();

			Hand_RoleSelect hand_RoleSelect1 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect2 = new Hand_RoleSelect();
			Hand_RoleSelect hand_RoleSelect3 = new Hand_RoleSelect();
			/*
			 * Hand_RoleSelect hand_RoleSelect4 = new Hand_RoleSelect(); Hand_RoleSelect
			 * hand_RoleSelect5 = new Hand_RoleSelect(); Hand_RoleSelect hand_RoleSelect6 =
			 * new Hand_RoleSelect();
			 */
			hand_RoleSelect1.setRoleType(RoleType.PARENT);
			hand_RoleSelect1.setRoleName(RoleType.PARENT_NAME);

			hand_RoleSelect2.setRoleType(RoleType.PRINCIPAL);
			hand_RoleSelect2.setRoleName(RoleType.PRINCIPAL_NAME);

			hand_RoleSelect3.setRoleType(RoleType.TEACHER);
			hand_RoleSelect3.setRoleName(RoleType.TEACHER_NAME);

			/*
			 * hand_RoleSelect4.setRoleType(RoleType.DOCTOR);
			 * hand_RoleSelect4.setRoleName(RoleType.DOCTOR_NAME);
			 * 
			 * hand_RoleSelect5.setRoleType(RoleType.SERVICE);
			 * hand_RoleSelect5.setRoleName(RoleType.SERVICE_NAME);
			 * 
			 * hand_RoleSelect6.setRoleType(RoleType.ASSISTANT);
			 * hand_RoleSelect6.setRoleName(RoleType.ASSISTANT_NAME);
			 */

			// 家长
			List<Out_Student_Search> studenList = userService.getStudentListByOpenid(openId);
			for (Out_Student_Search student : studenList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setId(student.getId());
				hand_Role_Weichat.setParentId(student.getParentId());
				hand_Role_Weichat.setSchoolId(student.getSchoolId());
				hand_Role_Weichat.setSchoolName(student.getSchooltitle());
				hand_Role_Weichat.setStudentId(student.getStudentId());
				hand_Role_Weichat.setStudentName(student.getName());
				hand_RoleSelect1.getList().add(hand_Role_Weichat);
			}
			// 园长
			List<Hand_staff> schoolList = userService.getSchoolListByOpenid(openId);
			for (Hand_staff school : schoolList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setId(school.getId());
				hand_Role_Weichat.setStaffId(school.getStaffId());
				
				hand_Role_Weichat.setSchoolId(school.getSchoolId());
				hand_Role_Weichat.setSchoolName(school.getSchoolName());
				hand_RoleSelect2.getList().add(hand_Role_Weichat);
			}
			// 老师
			List<Hand_staff> teacherList = userService.getTeacherByOpenid(openId);
			for (Hand_staff staff : teacherList) {
				Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
				hand_Role_Weichat.setId(staff.getId());
				hand_Role_Weichat.setStaffId(staff.getStaffId());
				hand_Role_Weichat.setClassId(staff.getClassId());
				hand_Role_Weichat.setClassName(staff.getClassName());
				hand_Role_Weichat.setSchoolId(staff.getSchoolId());
				hand_Role_Weichat.setSchoolName(staff.getSchoolName());
				hand_RoleSelect3.getList().add(hand_Role_Weichat);
			}
			// 医生,后勤,行政暂时没有做
			hand_RoleSelect1.setNum(hand_RoleSelect1.getList().size());
			hand_RoleSelect2.setNum(hand_RoleSelect2.getList().size());
			hand_RoleSelect3.setNum(hand_RoleSelect3.getList().size());
			/*
			 * hand_RoleSelect4.setNum(hand_RoleSelect4.getList().size());
			 * hand_RoleSelect5.setNum(hand_RoleSelect5.getList().size());
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
			}
			/*
			 * if (hand_RoleSelect4.getNum() > 0) { hand_RoleSelects.add(hand_RoleSelect4);
			 * } if (hand_RoleSelect5.getNum() > 0) {
			 * hand_RoleSelects.add(hand_RoleSelect5); } if (hand_RoleSelect6.getNum() > 0)
			 * { hand_RoleSelects.add(hand_RoleSelect6); }
			 */

			back.setBackTypeWithLog(hand_RoleSelects, BackType.SUCCESS_SEARCH_NORMAL);

		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (UnknownAccountException e) {
			// 帐号不存在异常;
			logger.warn(e.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_UNKNOWN_USER, e.getMessage());
		} catch (IncorrectCredentialsException e) {
			// 帐号或密码错误异常;
			logger.warn(e.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_INCORRECT_USER, e.getMessage());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage());
		}

		return back;
	}

	/*
	 * 获取登录用户的信息,如果有获取失败返回,获取成功返回角色的多个列表;
	 */
	/*
	 * @ApiOperation(value = "用户登录,加载当前用户的角色列表", notes = "用户登录,加载当前用户的角色列表")
	 * 
	 * @PostMapping(value = "getRoleSelect")
	 * 
	 * @ResponseBody public Out<ArrayList<Hand_RoleSelect>> getRoleSelect(
	 * 
	 * @ApiParam(value = "用户登录", required = true) @RequestBody In_User_Login inUser,
	 * HttpServletRequest request, HttpServletResponse response) {
	 * logger.info("访问 LoginControler: getRoleSelect");
	 * Out<ArrayList<Hand_RoleSelect>> back = new Out<ArrayList<Hand_RoleSelect>>();
	 * ArrayList<Hand_RoleSelect> hand_RoleSelects = new
	 * ArrayList<Hand_RoleSelect>(); Tb_user user = null; List<Tb_role> roleList =
	 * null; List<Tb_school> schoolList = null; List<Out_Student_Search> studentList
	 * = null; try { // 获取账号信息 user = new Tb_user(); String oriId =
	 * "gh_c0a5d7478a57"; AuthUtil authUtil = new AuthUtil();
	 * 
	 * ExecutorService executorService = Executors.newFixedThreadPool(1); Runnable
	 * runnable = new Runnable() {
	 * 
	 * @Override public void run() { try {
	 * System.out.println("=====================1");
	 * System.out.println(Thread.currentThread().getName() + ": testRetry");
	 * authUtil.initAuth(oriId); System.out.println("=====================2"); }
	 * catch (Exception e) { e.printStackTrace(); } } }; Future<?> submit1 =
	 * executorService.submit(runnable); submit1.get();
	 * 
	 * WxMpOAuth2Servlet wxMpOAuth2Servlet = new WxMpOAuth2Servlet();
	 * wxMpOAuth2Servlet.service(request, response); WxMpUser wxMpUser = (WxMpUser)
	 * request.getSession().getAttribute("wxMpUser"); //
	 * BeanUtils.copyProperties(user, inUser); user.setOpenid(wxMpUser.getOpenId());
	 * logger.info("openid数据为:" + wxMpUser.getOpenId());
	 * user.setMobile("13592780692"); String openId = user.getOpenid(); String
	 * mobile = user.getMobile(); logger.info("openid验证数据为:" +
	 * wxMpUser.getOpenId()); logger.info("mobile验证数据为:" + mobile); boolean
	 * rememberMe = false; UsernamePasswordToken token = new
	 * UsernamePasswordToken(openId, mobile, rememberMe); Subject subject =
	 * SecurityUtils.getSubject(); subject.login(token); user = (Tb_user)
	 * subject.getPrincipal(); // 根据用户id查询用户拥有的角色集合; System.out.println(user);
	 * System.out.println(user.getId()); System.out.println(user.getMobile()); //
	 * 取数据 roleList = roleService.findAllRolesByUserId(user.getId());
	 * 
	 * // 组织数据
	 * 
	 * // switch 将所有的角色类型考虑到; for (Tb_role role : roleList) { switch
	 * (role.getType()) { case RoleType.PARENT: { // 家长类型; studentList =
	 * userService.getStudentListByMobile(mobile); Hand_RoleSelect hand_RoleSelect =
	 * new Hand_RoleSelect(); hand_RoleSelect.setRoleName(role.getName());
	 * hand_RoleSelect.setNum(studentList.size());
	 * hand_RoleSelect.setRoleType(role.getType()); for (Out_Student_Search student
	 * : studentList) { Hand_Role_Weichat hand_Role_Weichat = new
	 * Hand_Role_Weichat(); if (student.getClass_id() != null) {
	 * hand_Role_Weichat.setClassId(student.getClass_id()); } if (student.getName()
	 * != null) { hand_Role_Weichat.setStudentName(student.getName()); } if
	 * (student.getClasstitle() != null) {
	 * hand_Role_Weichat.setClassName(student.getClasstitle()); } if
	 * (student.getSchooltitle() != null) {
	 * hand_Role_Weichat.setSchoolName(student.getSchooltitle()); } if
	 * (student.getSchool_id() != null) {
	 * hand_Role_Weichat.setSchoolId(student.getSchool_id()); } String relation =
	 * student.getRelation_title(); // 当relation有值，取值，如果为null，取id对应的值; if (relation
	 * == null || "".equals(relation)) { relation =
	 * userService.getRelationByRelationId(student.getRelation_id()); } if (relation
	 * != null) { hand_Role_Weichat.setRelation(relation); } if
	 * (student.getParent_id() != null) {
	 * hand_Role_Weichat.setParentId(student.getParent_id()); } if
	 * (student.getClass_id() != null) {
	 * hand_Role_Weichat.setClassId(student.getClass_id()); } if (student.getId() !=
	 * null) { hand_Role_Weichat.setStudentId(student.getId()); } // 添加到集合
	 * hand_RoleSelect.getList().add(hand_Role_Weichat); }
	 * hand_RoleSelects.add(hand_RoleSelect);
	 * 
	 * } break; // 单个角色数据 // 角色数据：手动填入 // 学生数据：找到学生ID所对应数据学生数据
	 * 
	 * case RoleType.TEACHER: { Hand_teacher teacher =
	 * userService.getTeacherByMobile(mobile); Hand_RoleSelect hand_RoleSelect = new
	 * Hand_RoleSelect(); hand_RoleSelect.setRoleName(role.getName());
	 * hand_RoleSelect.setNum(1); hand_RoleSelect.setRoleType(role.getType());
	 * Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat(); if
	 * (teacher.getStaff_id() != null) {
	 * hand_Role_Weichat.setStaffId(teacher.getStaff_id()); } if
	 * (teacher.getSchool_id() != null) {
	 * hand_Role_Weichat.setSchoolId(teacher.getSchool_id()); ; } if
	 * (teacher.getTitle() != null) {
	 * hand_Role_Weichat.setSchoolName(teacher.getTitle()); }
	 * hand_RoleSelect.getList().add(hand_Role_Weichat);
	 * hand_RoleSelects.add(hand_RoleSelect); } break; case RoleType.PRINCIPAL: { //
	 * 园长类型; schoolList = userService.getSchoolListByMobile(mobile); Hand_RoleSelect
	 * hand_RoleSelect = new Hand_RoleSelect();
	 * hand_RoleSelect.setRoleName(role.getName());
	 * hand_RoleSelect.setNum(schoolList.size());
	 * hand_RoleSelect.setRoleType(role.getType()); for (Tb_school school :
	 * schoolList) { Hand_Role_Weichat hand_Role_Weichat = new Hand_Role_Weichat();
	 * 
	 * if (school.getTitle() != null) {
	 * hand_Role_Weichat.setSchoolName(school.getTitle()); }
	 * 
	 * if (school.getId() != null) { hand_Role_Weichat.setSchoolId(school.getId());
	 * } // 添加到集合 hand_RoleSelect.getList().add(hand_Role_Weichat); }
	 * hand_RoleSelects.add(hand_RoleSelect); } break; case RoleType.ASSISTANT:
	 * break; case RoleType.DOCTOR: break; case RoleType.SERVICE: break; default:
	 * logger.info("没有当前角色.."); } } // 家长 // 单个角色数据 // 角色数据：手动填入 //
	 * 学生数据：找到学生ID所对应数据学生数据
	 * 
	 * // 老师
	 * 
	 * // 园长 // 行政 // 医生 // 后勤
	 * 
	 * back.setBackTypeWithLog(hand_RoleSelects, BackType.SUCCESS_SEARCH_NORMAL);
	 * 
	 * 
	 * 
	 * } catch (ServiceException e) { // 服务层错误，包括 内部service 和 对外service
	 * logger.warn(e.getMessage());
	 * back.setServiceExceptionWithLog(e.getExceptionEnums()); } catch
	 * (UnknownAccountException e) { // 帐号不存在异常; logger.warn(e.getMessage());
	 * back.setBackTypeWithLog(BackType.FAIL_SEARCH_UNKNOWN_USER, e.getMessage()); }
	 * catch (IncorrectCredentialsException e) { // 帐号或密码错误异常;
	 * logger.warn(e.getMessage());
	 * back.setBackTypeWithLog(BackType.FAIL_SEARCH_INCORRECT_USER, e.getMessage());
	 * } catch (Exception ex) { logger.warn(ex.getMessage());
	 * back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage()); }
	 * 
	 * return back; }
	 */

	/*
	 * @RequestMapping("/login") public String login(){ return "login"; }
	 */

	/*
	 * 获取登录用户的信息,如果有多个角色,返回多个角色的列表;
	 */
	/*
	 * @ApiOperation(value = "用户登录,加载当前用户的角色列表", notes = "用户登录,加载当前用户的角色列表")
	 * 
	 * @PostMapping(value = "login")
	 * 
	 * @ResponseBody public Out<OutList<Tb_role>> login(@ApiParam(value = "用户登录",
	 * required = true) @RequestBody In_User_Login inUser){
	 * 
	 * logger.info("访问  LoginController:login, user=" + inUser); Tb_user user =
	 * null; List<Tb_role> roleList = null; OutList<Tb_role> outList = null;
	 * Out<OutList<Tb_role>> back = new Out<OutList<Tb_role>>();
	 * 
	 * try { user = new Tb_user(); BeanUtils.copyProperties(user, inUser); String
	 * openId = user.getOpenid(); String mobile = user.getMobile(); boolean
	 * rememberMe = false; UsernamePasswordToken token = new
	 * UsernamePasswordToken(openId, mobile, rememberMe); Subject subject =
	 * SecurityUtils.getSubject(); subject.login(token); user = (Tb_user)
	 * subject.getPrincipal(); //根据用户id查询用户拥有的角色集合; System.out.println(user);
	 * System.out.println(user.getId()); System.out.println(user.getMobile());
	 * roleList = roleService.findAllRolesByUserId(user.getId()); outList = new
	 * OutList<Tb_role>(roleList.size(), roleList); back.setBackTypeWithLog(outList,
	 * BackType.SUCCESS_SEARCH_NORMAL); } catch (ServiceException e) { // 服务层错误，包括
	 * 内部service 和 对外service logger.warn(e.getMessage());
	 * back.setServiceExceptionWithLog(e.getExceptionEnums()); } catch
	 * (UnknownAccountException e) { // 帐号或密码错误异常; logger.warn(e.getMessage());
	 * back.setMessage("未知帐号"); } catch (Exception ex) {
	 * logger.warn(ex.getMessage());
	 * back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage()); }
	 * 
	 * return back; }
	 * 
	 * 
	 * 如果用户是多个学校的园长,选择其中一个学校;
	 * 
	 * @ApiOperation(value = "根据园长电话获取学校列表", notes = "根据园长电话获取学校列表")
	 * 
	 * @PostMapping(value = "getSchoolListByMobile")
	 * 
	 * @ResponseBody public Out<OutList<Tb_school>> getSchoolListByMobile(String
	 * mobile){
	 * 
	 * logger.info("访问  LoginController:getSchoolListByMobile"); List<Tb_school>
	 * schoolList = null; OutList<Tb_school> outList = null; Out<OutList<Tb_school>>
	 * back = new Out<OutList<Tb_school>>();
	 * 
	 * try { //根据家长电话查询学生的集合; schoolList =
	 * userService.getSchoolListByMobile(mobile); outList = new
	 * OutList<Tb_school>(schoolList.size(), schoolList);
	 * back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL); } catch
	 * (ServiceException e) { // 服务层错误，包括 内部service 和 对外service
	 * logger.warn(e.getMessage());
	 * back.setServiceExceptionWithLog(e.getExceptionEnums()); } catch (Exception
	 * ex) { logger.warn(ex.getMessage());
	 * back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage()); }
	 * 
	 * return back; }
	 * 
	 * 
	 * 如果用户是多个学生的家长,选择其中一个学生;
	 * 
	 * @ApiOperation(value = "根据家长电话获取学生列表", notes = "根据家长电话获取学生列表")
	 * 
	 * @PostMapping(value = "getStudentListByMobile")
	 * 
	 * @ResponseBody public Out<OutList<Tb_student>> getStudentListByMobile(String
	 * parentPhone){
	 * 
	 * logger.info("访问  LoginController:getStudentListByMoblile"); List<Tb_student>
	 * studentList = null; OutList<Tb_student> outList = null;
	 * Out<OutList<Tb_student>> back = new Out<OutList<Tb_student>>();
	 * 
	 * try { //根据家长电话查询学生的集合; studentList =
	 * userService.getStudentListByMobile(parentPhone); outList = new
	 * OutList<Tb_student>(studentList.size(), studentList);
	 * back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL); } catch
	 * (ServiceException e) { // 服务层错误，包括 内部service 和 对外service
	 * logger.warn(e.getMessage());
	 * back.setServiceExceptionWithLog(e.getExceptionEnums()); } catch (Exception
	 * ex) { logger.warn(ex.getMessage());
	 * back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage()); }
	 * 
	 * return back; }
	 */

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
