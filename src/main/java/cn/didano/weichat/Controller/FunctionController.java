package cn.didano.weichat.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.FunctionService;
import cn.didano.weichat.Service.UserService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.constant.RoleType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Tb_function;
import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "功能服务", tags = "功能服务")
@RestController
@RequestMapping(value = "/base/function/post/")
public class FunctionController {
	
	static Logger logger = Logger.getLogger(FunctionController.class);
	
	@Autowired
	private FunctionService functionService;
	@Autowired
	private UserService userService;
	
	/*
	 * 获取欢迎页面区域功能项目;
	 */
	@ApiOperation(value = "功能项目查询", notes = "欢迎页面按分区进行功能项目查询")
	@GetMapping(value = "getAreaItems/{roletype}")
	@ResponseBody
	public Out<List<Tb_function>> addFunctionsToRole(@PathVariable Integer roletype) {
		Out<List<Tb_function>> back = new Out<>();
		List<Tb_function> parentFunctionList = new LinkedList<>();
		List<Tb_function> principalFunctionList = new LinkedList<>();
		List<Tb_function> teacherFunctionList = new LinkedList<>();
		try {
			List<Tb_function> functionList = functionService.findAreaItems(5);
			for (Tb_function tb_function : functionList) {
				switch(tb_function.getId()) {
					case 6: { // 园长信箱;
						principalFunctionList.add(tb_function);
						parentFunctionList.add(tb_function);
					}
					break;
					case 7: { // 通知公告;
						principalFunctionList.add(tb_function);
						teacherFunctionList.add(tb_function);
					}
					break;
					case 8: { // 小红花;
						teacherFunctionList.add(tb_function);
						parentFunctionList.add(tb_function);
					}
					break;
					case 9: { // 校园风采;
						principalFunctionList.add(tb_function);
						teacherFunctionList.add(tb_function);
					}
					break;
					case 10: { // 看宝宝;
						principalFunctionList.add(tb_function);
						teacherFunctionList.add(tb_function);
						parentFunctionList.add(tb_function);
					}
					break;
					case 11: { // 图文发布;
						principalFunctionList.add(tb_function);
						teacherFunctionList.add(tb_function);
					}
					break;
					case 12: { // 照片墙;
						parentFunctionList.add(tb_function);
					}
					break;
					case 13: { // 健康查询;
						parentFunctionList.add(tb_function);
					}
					break;
					case 14: { // 食谱;
						principalFunctionList.add(tb_function);
						teacherFunctionList.add(tb_function);
						parentFunctionList.add(tb_function);
					}
					break;
					case 15: { // 班级相册;
						principalFunctionList.add(tb_function);
						teacherFunctionList.add(tb_function);
					}
					break;
					case 16: { // 考勤;
						parentFunctionList.add(tb_function);
					}
					break;
					default:{
						logger.info("当前角色没有这个的栏目功能!!");
					}
				}
			}
			if (roletype == RoleType.PRINCIPAL) {
				back.setBackTypeWithLog(principalFunctionList, BackType.SUCCESS_SEARCH_NORMAL);
			}else if (roletype == RoleType.TEACHER) {
				back.setBackTypeWithLog(teacherFunctionList, BackType.SUCCESS_SEARCH_NORMAL);
			}else if (roletype == RoleType.PARENT) {
				back.setBackTypeWithLog(parentFunctionList, BackType.SUCCESS_SEARCH_NORMAL);
			}
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return back;
	}
	
	/*
	 * 获取欢迎页面区域功能项目;
	 */
	@ApiOperation(value = "功能项目查询", notes = "欢迎页面按分区进行功能项目查询")
	@PostMapping(value = "getAreaItems/{parentId}")
	@ResponseBody
	public Out<OutList<Tb_function>> getAreaItems(@PathVariable("parentId")Integer parentId) {
		
		logger.info("访问  FunctionController");
		Subject subject = SecurityUtils.getSubject();
		Tb_user user = (Tb_user) subject.getPrincipal();
		List<Tb_function> functionList = null;
		OutList<Tb_function> outList = null;
		Out<OutList<Tb_function>> back = new Out<OutList<Tb_function>>();
		
		try {
			Set<Tb_role> roles = user.getRoles();
			Tb_role activeRole = userService.getUserActiveRole(user.getId());
			for (Tb_role tb_role : roles) {
				if (tb_role.getId() != null && activeRole.getId().equals(tb_role.getId())) {
					
				}
			}
			functionList = functionService.findAreaItems(parentId);
			outList = new OutList<Tb_function>(functionList.size(), functionList);
			back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, ex.getMessage());
		}
		return back;
	}
	
	
}
