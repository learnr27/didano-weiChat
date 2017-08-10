package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.DreamService;
import cn.didano.weichat.Service.HobbyService;
import cn.didano.weichat.Service.StudentService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.In_StudentSetting_Edit;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_parentAndStudentId;
import cn.didano.weichat.model.Tb_dream;
import cn.didano.weichat.model.Tb_hobby;
import cn.didano.weichat.model.Tb_student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "微信服务", tags = "微信服务，提供给表现层")
@RestController
@RequestMapping(value = "/base/weiChat/post/")
public class ParentController {
	static Logger logger = Logger.getLogger(ParentController.class);
	@Autowired
	private StudentService studentService;
	@Autowired
	private HobbyService hobbyService;
	@Autowired
	private DreamService dreamService;

	/**
	 * 根据家长电话查看所有孩子
	 *
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "studentfindByParentPhone/{parentPhone}")
	@ApiOperation(value = "根据家长电话查看所有孩子", notes = "根据家长电话查看所有孩子")
	@ResponseBody
	public Out<OutList<Tb_student>> studentfindByParentPhone(@PathVariable("parentPhone") String parentPhone) {
		logger.info("访问  ParentController:studentfindByParentPhone,student_id=" + parentPhone);
		List<Tb_student> students = null;
		OutList<Tb_student> outList = null;
		Out<OutList<Tb_student>> back = new Out<OutList<Tb_student>>();
		try {
			students = studentService.getStudentListByParentPhone(parentPhone);
			outList = new OutList<Tb_student>(students.size(), students);
			back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

	/**
	 * 查找所有爱好
	 *
	 */
	@PostMapping(value = "findAllHobby")
	@ApiOperation(value = "查找所有爱好", notes = "查找所有爱好")
	@ResponseBody
	public Out<OutList<Tb_hobby>> findAllHobby() {
		logger.info("访问  ParentController:findAllHobby");
		List<Tb_hobby> hobbys = null;
		OutList<Tb_hobby> outList = null;
		Out<OutList<Tb_hobby>> back = new Out<OutList<Tb_hobby>>();
		try {
			hobbys = hobbyService.getAllHobby();
			outList = new OutList<Tb_hobby>(hobbys.size(), hobbys);
			back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

	/**
	 * 查找所有梦想
	 *
	 */
	@PostMapping(value = "findAllDreams")
	@ApiOperation(value = "查找所有爱好", notes = "查找所有爱好")
	@ResponseBody
	public Out<OutList<Tb_dream>> findAllDreams() {
		logger.info("访问  ParentController:findAllDreams");
		List<Tb_dream> dreams = null;
		OutList<Tb_dream> outList = null;
		Out<OutList<Tb_dream>> back = new Out<OutList<Tb_dream>>();
		try {
			dreams = dreamService.getAllDreams();
			outList = new OutList<Tb_dream>(dreams.size(), dreams);
			back.setBackTypeWithLog(outList, BackType.SUCCESS_SEARCH_NORMAL);
		} catch (ServiceException e) {
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		}
		return back;
	}

	/**
	 * 编辑首页学生相关信息
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = "编辑首页学生相关信息", notes = "编辑首页学生相关信息")
	@PostMapping(value = "student_setting")
	@ResponseBody
	public Out<String> student_setting(
			@ApiParam(value = "编辑首页学生相关信息", required = true) @RequestBody In_StudentSetting_Edit student_e) {
		logger.info("访问  ParentController:student_setting,student_e=" + student_e);

		Tb_student student = null;
		Hand_parentAndStudentId parentAndStudent = new Hand_parentAndStudentId();
		parentAndStudent.setParentId(student_e.getParentId());
		parentAndStudent.setStudentId(student_e.getStudentId());
		;
		Out<String> back = new Out<String>();

		try {
			student = studentService.getStudentByParentId(parentAndStudent);
			BeanUtils.copyProperties(student, student_e);
			int rowNum = studentService.updateStudentSetting(student);
			if (rowNum > 0) {
				back.setBackTypeWithLog(BackType.SUCCESS_UPDATE, "Id=" + "," + ":rowNum=" + rowNum);

			} else {
				// 更新有问题
				back.setBackTypeWithLog(BackType.FAIL_UPDATE_NORMAL, "rowNum=" + rowNum);
			}
			// end else

		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, ex.getMessage());
		}
		return back;
	}
}
