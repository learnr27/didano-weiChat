package cn.didano.weichat.Controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.ParentService;
import cn.didano.weichat.Service.StudentParentService;
import cn.didano.weichat.Service.UserService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Tb_school_parent;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_user;
import cn.didano.weichat.util.Hand_StudentParent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/*
 * 数据迁移controller;
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/

@Api(value = "数据库导入服务", tags = "数据库导入服务")
@RestController
@RequestMapping(value = "/base")
public class DataTransferController {

	static Logger logger = Logger.getLogger(DataTransferController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private ParentService parentService;
	
	@Autowired
	private StudentParentService studentParentService;
	
	@ApiOperation(value = "数据库tb_user表从指定行开始删除数据", notes = "数据库tb_user表从指定行开始删除数据")
	@PostMapping(value = "userDataDelete")
	@ResponseBody
	public Out<String> userDataDelete(@RequestBody String isdo, @RequestBody Integer row) {
		logger.info("访问 DataTransferController类: userDataDelete方法");
		logger.info("isdo="+isdo);
		Out<String> back = new Out<>();
		if ("do".equals(isdo)) {
			try {
				Integer rowNum = 0;
				rowNum = userService.deleteDatafrom(row);
				if (rowNum > 0) {
					back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "总共删除的条目数为 rowNum=" + rowNum);
				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "rowNum=" + rowNum);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "操作非法,不能执行...");
		}
		return back;
	}
	
	/*
	 * 用户表数据的迁移,将其它表中已有的数据迁移过来;
	 */
	@ApiOperation(value = "执行导入数据到数据库tb_user表", notes = "执行导入数据到数据库tb_user表")
	@PostMapping(value = "userDataTransfer")
	@ResponseBody
	public Out<String> userDataTransfer(@RequestBody String isdo) {
		logger.info("访问 DataTransferController类: userDataTransfer方法");
		logger.info("isdo="+isdo);
		// Map<String, Tb_user> userMap = new HashMap<>();
		Out<String> back = new Out<>();
		if ("do".equals(isdo)) {
			try {
				Integer rowNum = 0;
				List<Tb_user> userList = null;
				//查询用户绑定表的用户集合,取基本信息;
				userList = userService.getUserListFromOthers();
				// userList = handUserMapper.getUserListFromOthers();
				for (Tb_user tb_user : userList) {
					String phone = tb_user.getPhone();
					Date now = new Date();
					tb_user.setDeleted(false);
					tb_user.setCreated(now);
					tb_user.setUpdated(now);
					// 根据绑定表的电话,查询家长的数据集合;
					List<Hand_StudentParent> parentList = userService.getStudentParentListByPhone(phone);
					// 根据绑定表的电话,查询组织的数据集合;
					List<Tb_staff> staffList = userService.getStaffByPhoneFromOthers(phone);
					if (parentList == null && staffList == null) {
						userService.save(tb_user);
						rowNum++;
					}
					logger.info("parentList大小值为: "+parentList.size());
					if (parentList != null && parentList.size() > 0) {
						for (Hand_StudentParent studentParent : parentList) {
							logger.info("进入插入parent方法");
							tb_user.setStaffId(null);
							tb_user.setSchoolParentId(studentParent.getParentId());
							tb_user.setStudentId(studentParent.getStudentId());
							tb_user.setType(30);
							userService.save(tb_user);
							logger.info("插入1次parent数据");
							rowNum++;
						}
					}
					logger.info("staffList大小值为: "+staffList.size());
					if (staffList != null && staffList.size() > 0) {
						for (Tb_staff tb_staff : staffList) {
							logger.info("进入插入staff方法");
							tb_user.setSchoolParentId(null);
							tb_user.setStudentId(null);
							tb_user.setStaffId(tb_staff.getId());
							int type = tb_staff.getType();
							tb_user.setType(type);
							userService.save(tb_user);
							logger.info("插入1次staff数据");
							rowNum++;
						}
					}
					logger.info("已插入的条目数为:   "+rowNum);
				}
				if (rowNum > 0) {
					back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "总共插入的条目数为 rowNum=" + rowNum);
					
				} else {
					// 更新有问题
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NO_INSERT, "rowNum=" + rowNum);
				}
				logger.info("总共插入的条目数为:   "+rowNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NO_INSERT, "操作非法,不能执行...");
		}
		return back;
	}
	
	/**
	 * 补录数据
	 * 确保student_parent 有多少条数据，那么school_parent表就有多少数据
	 * @param isdo
	 * @return
	 */
	@ApiOperation(value = "数据库tb_school_parent表补录数据", notes = "数据库tb_school_parent表补录数据")
	@PostMapping(value = "supplyData2schoolParentTable")
	@ResponseBody
	public Out<String> supplyData2schoolParentTable(@RequestBody String isdo) {
		logger.info("访问 DataTransferController类: supplyData2schoolParentTable方法");
		logger.info("isdo="+isdo);
		Out<String> back = new Out<>();
		List<Tb_student_parent> studentParentlist;
		List<Tb_school_parent> parentlist;
		if("do".equals(isdo)){
			//getDuplicateStudentParentData 先获取有重复 student_parent ,
			try {
				studentParentlist= userService.getDuplicateStudentParentData();
				parentlist= userService.getDuplicateParentData();
				int count=0;
				//外部以重复家长数目循环，内部以单个家长多个学生循环
				for(Tb_school_parent parent:parentlist){
					count++;
					logger.info("count="+count+"tp.getId()="+parent.getId());
					Tb_school_parent tmp = new Tb_school_parent();
					BeanUtils.copyProperties(tmp, parent);
					tmp.setId(null);
					tmp.setUpdated(null);
					int check = 0;
					int i = 0;
					int size = studentParentlist.size();
					for(Tb_student_parent studentParent:studentParentlist){
						i++;
						logger.info("tsp.getId()="+studentParent.getId()+":parent.getId()="+parent.getId()+":tsp.getParentId()="+studentParent.getParentId());
						if(parent.getId().equals(studentParent.getParentId())){
							check++;
							logger.info("check="+check+"|parent.getId()="+parent.getId());
							//超过一条就插入
							if(check>1){
								logger.info("check="+check);
								logger.info("ready insert tb_school_parent...");
								tmp.setSchoolId(studentParent.getSchoolId());
								parentService.insertSelective(tmp);
								logger.info("insert one..."+tmp.getId());
								studentParent.setParentId(tmp.getId());
								//更新parentId
								studentParentService.updateByPrimaryKey(studentParent);
								logger.info("update tb_student_parent..."+studentParent.getId());
							}
						}
						if(i==size){
							if(check==0){
								logger.info("no found,studentParent.getParentId="+studentParent.getParentId());
							}else if(check==1){
								logger.info("found check==1,studentParent.getParentId="+studentParent.getParentId());
							}else{
								logger.info("found,check=="+check+",studentParent.getParentId="+studentParent.getParentId());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			// 更新有问题
			back.setBackTypeWithLog(BackType.FAIL_DB, "没做任何操作");
		}
		return back;
	}
}
