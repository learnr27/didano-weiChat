package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.Service.AttendanceService;
import cn.didano.weichat.constant.BackType;
import cn.didano.weichat.exception.ServiceException;
import cn.didano.weichat.json.Out;
import cn.didano.weichat.model.Hand_attendanceData;
import cn.didano.weichat.model.Hand_classArriveAndLeaveInfo;
import cn.didano.weichat.model.Hand_classArriveData;
import cn.didano.weichat.model.Tb_staff;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "考勤服务", tags = "考勤服务，提供给表现层")
@RestController
@RequestMapping(value = "/base/attendanc/post/")
public class AttendanceController {
	static Logger logger = Logger.getLogger(AttendanceController.class);
    
	@Autowired
	private AttendanceService attendanceService;
	
	/**
	 * 根据用户id,时间查找班级应到人数实到人数未到人数
	 *老师角色
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "classAttendance_findtByTeacher/{staff_id}/{date}")
	@ApiOperation(value = "根据用户id,时间查找班级应到人数实到人数未到人数", notes = "根据用户id,时间查找班级应到人数实到人数未到人数")
	@ResponseBody
	public Out<Hand_classArriveData> notice_findtByUserid(@PathVariable("staff_id") Integer staff_id,@PathVariable("date") String date) {
		logger.info("访问  AttendanceController:classAttendance_findtByTeacher,staff_id=" + staff_id);
		Tb_staff staff = attendanceService.findStaffInfoById(staff_id);
		Hand_classArriveData data = null;
		Hand_attendanceData attendanceDate = null;
		List<Hand_classArriveAndLeaveInfo> infos = null;
		Hand_classArriveAndLeaveInfo info = null;
		Out<Hand_classArriveData> back = new Out<Hand_classArriveData>();
		try {
			data = new Hand_classArriveData();
			attendanceDate = new Hand_attendanceData();
			attendanceDate.setSchool_id(staff.getSchoolId());
			attendanceDate.setDate(date);
			//查询该学校所有班级到达人数
			infos = attendanceService.findClassArriveNumBySchool(attendanceDate);
			//设置该班级总共人数
			data.setAllNum(attendanceService.findAllStudentNumByClass(staff.getClass_id()));
			//获取该班级到达人数
			for (int i = 0; i < infos.size(); i++) {
				info = infos.get(i);
				if(info.getClass_id()==staff.getClass_id()){
					data.setArriveNum(info.getNum());
				}
			}
			//设置未到人数
			data.setNoArriveNum(data.getAllNum()-data.getArriveNum());
			data.setClassId(staff.getClass_id());
			back.setBackTypeWithLog(data, BackType.SUCCESS_SEARCH_NORMAL);
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
