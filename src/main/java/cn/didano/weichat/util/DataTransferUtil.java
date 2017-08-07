package cn.didano.weichat.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.didano.weichat.Controller.LoginController;
import cn.didano.weichat.Service.UserService;
import cn.didano.weichat.dao.Hand_userMapper;
import cn.didano.weichat.model.Tb_schoolParent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_user;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class DataTransferUtil {

	static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private Hand_userMapper handUserMapper;

	/*
	 * 用户表数据的迁移,从其它表中已有的数据迁移过来;
	 */
	/*@Test*/
	public void userDataTransfer() {
		// Map<String, Tb_user> userMap = new HashMap<>();
		try {
			List<Tb_user> userList = null;
			userList = userService.getUserListFromOthers();
			// userList = handUserMapper.getUserListFromOthers();
			for (Tb_user tb_user : userList) {
				String openid = tb_user.getOpenid();
				String mobile = tb_user.getMobile();
				// 查询数据库中是否还有多个重复的数据;
				// List<Tb_user> list = userService.getUserByOpenidFromOthers(openid);
				// 查询学校的数据;
				//List<Tb_school> schoolList = userService.getSchoolListByMobile(mobile);
				// 查询班级的数据;
				//List<Tb_class> classList = userService.get
				// 查询家长的数据;
				List<Tb_schoolParent> parentList = userService.getParentListByMobile(mobile);
				logger.info("parentList大小值为: "+parentList.size());
				if (parentList != null && parentList.size() > 0) {
					for (Tb_schoolParent tb_school_parent : parentList) {
						logger.info("进入插入staff方法");
						tb_user.setSchoolParentId(tb_school_parent.getId());
						userService.save(tb_user);
						logger.info("插入1次parent数据");
					}
				}
				// 查询组织的数据;
				List<Tb_staff> staffList = userService.getStaffByMobileFromOthers(mobile);
				logger.info("staffList大小值为: "+staffList.size());
				if (staffList != null && staffList.size() > 0) {
					for (Tb_staff tb_staff : staffList) {
						logger.info("进入插入parent方法");
						tb_user.setStaffId(tb_staff.getId());
						userService.save(tb_user);
						logger.info("插入1次staff数据");
					}
				}
				userService.save(tb_user);
				logger.info("插入1次数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
