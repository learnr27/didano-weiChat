package cn.didano.weichat.Controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.RoleService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Tb_function;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 角色服务,有角色功能列表的获取;
 */
@Api(value = "角色服务", tags = "角色功能服务")
@RestController
@RequestMapping(value = "/base/role/post/")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	static Logger logger = Logger.getLogger(RoleController.class);
	
	/*
	 * 角色功能列表的查询,角色的功能根据数据库表tb_role_function_rel查询得到;
	 */
	@ApiOperation(value="角色的功能列表查询",notes="角色的功能列表查询")
	@GetMapping(value = "/getAreaItems/{roletype}")
	@ResponseBody
	public Out<List<Tb_function>> getFunctionsToRole(@PathVariable Integer roletype) {
		Out<List<Tb_function>> back = new Out<>();
		try {
			List<Tb_function> functionList = roleService.getFunctionsByRoletype(roletype);
			back.setBackTypeWithLog(functionList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return back;
	}
	
	
}
