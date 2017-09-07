package cn.didano.weichat.articleController;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.ClassArticleService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_class;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "班级服务", tags = "班级服务")
@RestController
@RequestMapping(value = "/base/class/post/")
public class ClassController {
	static Logger logger = Logger.getLogger(ClassController.class);
    
	@Autowired
	private ClassArticleService classService;
	/**
	 * 根据班级名称模糊查询
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findClassName/{name}/{staff_id}")
	@ApiOperation(value = "根据班级名称模糊查询", notes = "根据班级名称模糊查询")
	@ResponseBody
	public Out<OutList<Hand_class>> findClassName(@PathVariable("name") String name,@PathVariable("staff_id") Integer staff_id) {
		logger.info("访问  findClassName,name=" + name);
		List<Hand_class> hand_class = null;
		Out<OutList<Hand_class>> back = new Out<OutList<Hand_class>>();
		OutList<Hand_class> outList=null;
		try {
			Hand_class findScholById = classService.findScholById(staff_id);
			name="%"+name+"%";
			Hand_class hc=new Hand_class();
			hc.setTitle(name);
			hc.setSchoolId(findScholById.getSchoolId());
			hand_class = classService.findClassName(hc);
			//判断是否查询到该教职工所在的学校id
			if(hand_class!=null){//说明查询成功
				 outList = new OutList<Hand_class>(hand_class.size(), hand_class);
				 back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			}else{
				back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, "该openId不是园长");
			}
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
	/**
	 * 使用职工id查询学校
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findSchoolAll/{openid}")
	@ApiOperation(value = "根据openid查询所有学校信息", notes = "根据openid查询所有学校信息")
	@ResponseBody
	public Out<OutList<Hand_class>> findSchoolAll(@PathVariable("openid") String openid) {
		logger.info("访问  findClassAll,staff_id=" + openid);
		List<Hand_class> hand_class = null;
		Out<OutList<Hand_class>> back = new Out<OutList<Hand_class>>();
		OutList<Hand_class> outList=null;
		try {
			//查询staff_id所在的学校
			hand_class = classService.findSchoolAll(openid);
			//判断是否查询到该教职工所在的学校id
			if(hand_class!=null){//说明查询成功
				 outList = new OutList<Hand_class>(hand_class.size(), hand_class);
				 back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			}else{
				back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, "该openId不是园长");
			}
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
	
	/**
	 * 查询某个学校所有班级
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findClassAll/{staff_id}")
	@ApiOperation(value = "根据staff_id查询所在学校所有班级信息", notes = "根据staff_id查询所在学校所有班级信息")
	@ResponseBody
	public Out<OutList<Hand_class>> findClassAll(@PathVariable("staff_id") Integer staff_id) {
		logger.info("访问  findClassAll,staff_id=" + staff_id);
		List<Hand_class> hand_class = new ArrayList<Hand_class>();
		Out<OutList<Hand_class>> back = new Out<OutList<Hand_class>>();
		OutList<Hand_class> outList=null;
		try {
			//查询staff_id所在的学校
			Hand_class findScholById = classService.findScholById(staff_id);
			//判断是否查询到该教职工所在的学校id
			if(findScholById!=null){//说明查询成功
				 //查询所有班级的信息
				hand_class = classService.findClassAll(findScholById.getSchoolId());
				 outList = new OutList<Hand_class>(hand_class.size(), hand_class);
				 back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
			}else{
				back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, "不存在该园长或者行政人员");
			}
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
	
	/**
	 * 查询某个学校所有班级
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "findClass/{class_id}")
	@ApiOperation(value = "根据班级id查询单个班级信息", notes = "根据班级id查询单个班级信息")
	@ResponseBody
	public Out<Hand_class> findClass(@PathVariable("class_id") Integer class_id) {
		logger.info("访问  findClass,class_id=" + class_id);
		Hand_class hand_class = new Hand_class();
		Out<Hand_class> back = new Out<Hand_class>();
		try {
			//直接查询班级信息
			hand_class=classService.findClass(class_id);
			if(hand_class!=null){
				back.setBackTypeWithLog(hand_class, BackType.SUCCESS_SEARCH_NORMAL);
			}else{
				back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL,"查询失败");
			}
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
	
	/**
	 * 某个学校的园长或者行政进行新班级的添加和修改
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "staffUpdateOrInsertClass")
	@ApiOperation(value = "添加班级/编辑班级", notes = "添加班级/编辑班级")
	@ResponseBody
	public Out<String> staffUpdateOrInsertClass(@ApiParam(value = "添加班级/编辑班级", required = true) @RequestBody Hand_class classInfo) {
		logger.info("访问  staffUpdateOrInsertClass,classInfo=" + classInfo);
		Out<String> back = new Out<String>();
		try {
			//判断class_id的值如果班级id为null，进行添加操作，反之进行修改操作
			if(classInfo.getId()!=null){
				//进行班级的修改操作
				System.out.println("执行修改操作");
				int updateClass = classService.updateClass(classInfo);
				System.out.println("修改完成");
				if(updateClass>0){
					back.setBackTypeWithLog(BackType.SUCCESS_UPDATE, "班级更新成功");
				}else{
					back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, "班级更新失败");
				}
			}else{
				//设置数据创建的时间
				classInfo.setCreated(new Date());
				//设置开学时间
				classInfo.setStart(new Date());
				int insertCalss = classService.insertCalss(classInfo);
				if(insertCalss>0){
					back.setBackTypeWithLog(BackType.SUCCESS_INSERT, "班级添加成功");
				}else{
					back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, "班级添加失败");
				}
			}
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
	
	
	/**
	 * 删除某个班级以及下面的所有学生，家长信息
	 */
	@PostMapping(value = "deletedClassAndSduent/{class_id}")
	@ApiOperation(value = "删除班级以及班级下面的所有学生", notes = "删除班级以及班级下面的所有学生")
	@ResponseBody
	public Out<String> deletedClassAndStudent(@PathVariable("class_id") Integer class_id) {
		logger.info("访问  deletedClassAndSduent,class_id=" + class_id);
		Out<String> back = new Out<String>();
		try {
			//判断班级的id是否为空
			if(class_id!=null){
				//判断班级下面是否有学生
				int findClassByStudent = classService.findClassByStudent(class_id);
				if(findClassByStudent!=0){
					//进行一级一级的删除
					//直接删除整个班级的学生家长信息
					classService.deletedParent(class_id);
					//删除学生信息
					classService.deletedStudentParent(class_id);
					//先去删除班级下面的学生以及家长，在删除班级信息
					classService.deletedStudent(class_id);
					//删除班级信息
					int deleted4=classService.deletedClass(class_id);
					if(deleted4>0){
						back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "删除班级成功");
					}else{
						back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "班级删除异常");
					}
				}else{
					//删除班级信息
					int deleted4=classService.deletedClass(class_id);
					if(deleted4>0){
						back.setBackTypeWithLog(BackType.SUCCESS_DELETE, "删除班级成功");
					}else{
						back.setBackTypeWithLog(BackType.FAIL_DELETE_NO_DELETE, "班级删除异常");
					}
				}
			}else{
				back.setBackTypeWithLog(BackType.FAIL_SEARCH_NORMAL, "班级删除失败，班级id不存在");
			}
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
