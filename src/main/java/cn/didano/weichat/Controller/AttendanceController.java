package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import cn.didano.weichat.model.Hand_clasStudentArriveAndLeave;
import cn.didano.weichat.model.Hand_classArriveAndLeaveInfo;
import cn.didano.weichat.model.Hand_classArriveData;
import cn.didano.weichat.model.Hand_studentArriveAndLeaveDate;
import cn.didano.weichat.model.Hand_teacherAttendance;
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
	@ApiOperation(value = "根据老师id,时间查找班级应到人数实到人数未到人数", notes = "根据老师id,时间查找班级应到人数实到人数未到人数")
	@ResponseBody
	public Out<Hand_classArriveData> notice_findtByUserid(@PathVariable("staff_id") Integer staff_id,@PathVariable("date") String date) {
		logger.info("访问  AttendanceController:classAttendance_findtByTeacher,staff_id=" + staff_id);
		Tb_staff staff = attendanceService.findStaffInfoById(staff_id);
		Hand_classArriveData data = null;
		Hand_attendanceData attendanceDate = null;
		List<Hand_classArriveAndLeaveInfo> infos = null;
		List<Hand_teacherAttendance> teacherAttendances= null;
		Hand_teacherAttendance teacherAttendance = null;
		Hand_classArriveAndLeaveInfo info = null;
		Out<Hand_classArriveData> back = new Out<Hand_classArriveData>();
		try {
			
			data = new Hand_classArriveData();
			attendanceDate = new Hand_attendanceData();
			attendanceDate.setSchool_id(staff.getSchool_id());
			attendanceDate.setDate(date);
			attendanceDate.setStaff_id(staff_id);
			//查询该学校所有班级到达人数
			infos = attendanceService.findClassArriveNumBySchool(attendanceDate);
			//设置该班级总共人数
			data.setAllNum(attendanceService.findAllStudentNumByClass(staff.getClass_id()));
			//获取该班级到达人数
			for (int i = 0; i < infos.size(); i++) {
				info = infos.get(i);
			     //当班级id与老师的班级id相同时，查看该班的已到人数
				if(info.getClass_id().equals(staff.getClass_id())){
					data.setArriveNum(info.getNum());
					
				}
			}
			
			//设置未到人数
			if(data.getArriveNum()!=null){
			data.setNoArriveNum(data.getAllNum()-data.getArriveNum());
			}else{//没有已到人数的情况
				data.setArriveNum(0);
				data.setNoArriveNum(data.getAllNum());
			}
			data.setClassId(staff.getClass_id());
			//截取时间的年月
			String month = attendanceDate.getDate().substring(0, 7);
			attendanceDate.setDate(month);
			//查找老师本月考勤数据
			teacherAttendances=attendanceService.findTeacherMonthAttendanceById(attendanceDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//设置老师考勤数据的时间
			for (int i = 0; i < teacherAttendances.size(); i++) {
				teacherAttendance=teacherAttendances.get(i);
				teacherAttendance.setSign_date(sdf.format(teacherAttendance.getCreated()));
			}
			data.setTeacherAttendance(teacherAttendances);
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
	
	/**
	 * 老师
	 * 查询某个班级所有人到校离校情况
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "studentAttendance_findtByClass/{class_id}/{date}")
	@ApiOperation(value = "老师查询某个班级所有人到校离校情况", notes = "老师查询某个班级所有人到校离校情况")
	@ResponseBody
	public Out<Hand_clasStudentArriveAndLeave> studentAttendance_findtByClass(@PathVariable("class_id") Integer class_id,@PathVariable("date") String date) {
		logger.info("访问  AttendanceController:studentAttendance_findtByClass,staff_id=" + class_id);
		Hand_clasStudentArriveAndLeave data = null;
		List<Hand_studentArriveAndLeaveDate> studentDate=null;
		Hand_attendanceData attendanceData=null;
		Hand_studentArriveAndLeaveDate arriveAndLeave= null;
		Out<Hand_clasStudentArriveAndLeave> back = new Out<Hand_clasStudentArriveAndLeave>();
		try {
			attendanceData= new Hand_attendanceData();
			data = new Hand_clasStudentArriveAndLeave();
			attendanceData.setClass_id(class_id);
			attendanceData.setDate(date);
			//查询到校离校数据
			studentDate = attendanceService.findStudentArriveAndLeaveByClass(attendanceData);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			//转换到校离校时间格式
			for (int i = 0; i < studentDate.size(); i++) {
				arriveAndLeave=studentDate.get(i);
				if(arriveAndLeave.getArriveTime()!=null){
				arriveAndLeave.setArrive(sdf.format(arriveAndLeave.getArriveTime()));
				}else{
					arriveAndLeave.setArrive("未到校");
				}
				if(arriveAndLeave.getLeaveTime()!=null){
				arriveAndLeave.setLeave(sdf.format(arriveAndLeave.getLeaveTime()));
				}else{
					arriveAndLeave.setLeave("未离校");
				}
			}
			data.setStudentDate(studentDate);
			data.setClass_id(class_id);
			if(studentDate!=null){
			data.setTitle(studentDate.get(0).getTitle());
			}
			
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
