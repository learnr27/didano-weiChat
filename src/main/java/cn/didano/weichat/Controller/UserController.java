package cn.didano.weichat.Controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.RoleService;
import cn.didano.weichat.Service.UserService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Tb_user;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "微信用户服务", tags = "用户服务")
@RestController
@RequestMapping(value = "/base/user/post/")
public class UserController {
	
	static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	/*
	 * 获取所有用户的列表;
	 */
	@ApiOperation(value = "获取用户列表", notes = "获取所有用户的列表")
	@PostMapping(value = "getAllUsers")
	@ResponseBody
	public Out<OutList<Tb_user>> getAllUsers() {
		
		logger.info("访问  UserController:getAllUsers");
		Tb_user user = null;
		List<Tb_user> userList = null;
		OutList<Tb_user> outList = null;
		Out<OutList<Tb_user>> back = new Out<OutList<Tb_user>>();
		
		try {
			userList = userService.findAllUsers();
			outList = new OutList<Tb_user>(userList.size(), userList);
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
	
	/*
	 * 获取所有用户的列表;
	 */
	@ApiOperation(value = "编辑用户", notes = "选择一个用户后,可以进行编辑")
	@PostMapping(value = "editUser/{user_id}")
	@ResponseBody
	public Out<String> editUser(@PathVariable("user_id") Tb_user user_id) {
		
		Out<String> back = new Out<>();
		Integer rowNum1 = null;
		Integer rowNum2 = null;
		int rowNum = userService.updateByPrimaryKeySelective(user_id);
		try {
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "rowNum1=" + rowNum1,"rowNum2="+rowNum2);
			}else {
				back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "rowNum1=" + rowNum1,"rowNum2="+rowNum2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return back;
	}
}
