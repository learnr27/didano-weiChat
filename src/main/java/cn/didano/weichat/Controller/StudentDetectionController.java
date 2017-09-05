package cn.didano.weichat.Controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.DreamService;
import cn.didano.weichat.Service.StudentService;
import cn.didano.weichat.config.OssInfo;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Tb_dream;
import cn.didano.weichat.model.Tb_student;
import cn.didano.weichat.model.Tb_student_detection4photoWall;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "晨检通知服务", tags = "晨检通知服务，提供给表现层")
@RestController
@RequestMapping(value = "/base/dectection/post/")
public class StudentDetectionController {

	static Logger logger = Logger.getLogger(StudentDetectionController.class);
	@Autowired
	private StudentService studentService;
	@Autowired
	private DreamService dreamService;
	@Autowired
	OssInfo ossInfo;

	/**
	 * 晨检信息
	 *
	 * @param c_channel
	 * @return
	 */
	@ApiOperation(value = " 晨检信息", notes = " 晨检信息")
	@PostMapping(value = "findStudentDetection/{student_id}")
	@ResponseBody
	public Out<Tb_student_detection4photoWall> findStudentDetection(
			@ApiParam(value = "晨检信息", required = true) @PathVariable("student_id") Integer student_id) {
		logger.info("StudentDetectionController :findStudentDetection,student_id=" + student_id);

		Tb_student student = null;
		Tb_dream dream = null;
		Tb_student_detection4photoWall data = null;
		Out<Tb_student_detection4photoWall> back = new Out<Tb_student_detection4photoWall>();
		try {
			student = studentService.selectStudentById(student_id);
			// 查找梦想
			if (student.getDreamId() != 0) {
				dream = dreamService.selectDreamById(student.getDreamId());
			}
			data = studentService.selectStudentDectection(student_id);
			data.setDream(dream.getName());
			if (student.getHobby() != null) {
				data.setHobby(student.getHobby());
			}
			// 转换单位
			data.setHeight(new BigDecimal(data.getHeight()/10).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());
			data.setWeight(new BigDecimal(data.getWeight()/1000).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());
			// 转换图片地址
			StringBuilder address = new StringBuilder(ossInfo.getImgPath());
			address.append(data.getOrgImgUrl());
			data.setOrgImgUrl(address.toString());
			back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);

		} catch (ServiceException e) {
			// 服务层错误，包括 内部service 和 对外service
			logger.warn(e.getMessage());
			back.setServiceExceptionWithLog(e.getExceptionEnums());
		} catch (Exception ex) {
			logger.warn(ex.getMessage());
			back.setBackTypeWithLog(BackType.FAIL_INSERT_NORMAL, ex.getMessage());
		}
		return back;
	}

}
