package cn.didano.weichat.Controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import cn.didano.weichat.json.OutList;
import cn.didano.weichat.model.Hand_attendanceData;
import cn.didano.weichat.model.Hand_clasStudentArriveAndLeave;
import cn.didano.weichat.model.Hand_classArriveAndLeaveInfo;
import cn.didano.weichat.model.Hand_classArriveData;
import cn.didano.weichat.model.Hand_schoolArriveDate;
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
	 * 老师角色
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "classAttendance_findtByTeacher/{staff_id}/{date}")
	@ApiOperation(value = "老师角色，根据老师id,时间查找班级应到人数实到人数未到人数", notes = "老师角色，根据老师id,时间查找班级应到人数实到人数未到人数")
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
	@ApiOperation(value = "老师角色，老师查询某个班级所有人到校离校情况", notes = "老师角色，老师查询某个班级所有人到校离校情况")
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
	
	/**
	 * 根据学校id查询该学校老师和学生到校情况
	 * 园长角色
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "schoolAttendance_findtByschoolId{school_id}/{date}")
	@ApiOperation(value = "园长角色，根据学校id查询该学校老师和学生到校情况", notes = "园长角色，根据学校id查询该学校老师和学生到校情况")
	@ResponseBody
	public Out<Hand_schoolArriveDate> schoolAttendance_findtByschoolId(@PathVariable("school_id") Integer school_id,@PathVariable("date") String date) {
		logger.info("访问  AttendanceController:schoolAttendance_findtByschoolIdr,school_id=" + school_id);
		Hand_schoolArriveDate schoolDate = null;
		Hand_attendanceData data = null;
		Out<Hand_schoolArriveDate> back = new Out<Hand_schoolArriveDate>();
		try {
			schoolDate = new Hand_schoolArriveDate();
			data = new Hand_attendanceData();
			data.setSchool_id(school_id);
			data.setDate(date);
			//获取该学校学生总人数
			int allStudent = attendanceService.getSchoolAllStudentNum(school_id);
			//获取该时间学校到校人数
			int arriveStudent = attendanceService.findSchoolStudentArriveNum(data);
			schoolDate.setAllStudentNum(allStudent);
			schoolDate.setStudentArriveNum(arriveStudent);
	        schoolDate.setStudentNoArriveNum(allStudent-arriveStudent);		
			//获取该学校员工总数
			int allStaff = attendanceService.getSchoolAllStaffNum(school_id);
			//获取该学校当天到的员工数
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//获取unix时间戳
			long sign_timestamp=sdf.parse(date).getTime()/1000;
			data.setSign_timestamp(sign_timestamp);
			int arriveStaff = attendanceService.getSchoolStaffArriveNum(data);
			schoolDate.setAllTeacherNum(allStaff);
			schoolDate.setTeacherArriveNum(arriveStaff);
			schoolDate.setTeacherNoArriveNum(allStaff-arriveStaff);
			back.setBackTypeWithLog(schoolDate, BackType.SUCCESS_SEARCH_NORMAL);
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
	 * 园长
	 * 查询某个学校员工到校离校情况
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "staffAttendance_findtBySchool/{school_id}/{date}")
	@ApiOperation(value = "园长角色，查询某个学校员工到校离校情况", notes = "园长角色，查询某个学校员工到校离校情况")
	@ResponseBody
	public Out<OutList<Tb_staff>> staffAttendance_findtBySchool(@PathVariable("school_id") Integer school_id,@PathVariable("date") String date) {
		logger.info("访问  AttendanceController:staffAttendance_findtBySchool,school_id=" + school_id);
	
		Hand_attendanceData attendanceData=null;
        List<Tb_staff> staffs=null;
		OutList<Tb_staff> outList = null;
		Out<OutList<Tb_staff>> back = new Out<OutList<Tb_staff>>();
		try {
			attendanceData= new Hand_attendanceData();
			attendanceData.setSchool_id(school_id);		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//转换为unix时间格式
			long sign_timestamp=sdf.parse(date).getTime()/1000;
			attendanceData.setSign_timestamp(sign_timestamp);
			staffs = attendanceService.getDaySignStatisticList(attendanceData);
			outList = new OutList<Tb_staff>(staffs.size(), staffs);
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
	
	/**
	 * 园长
	 * 查询某个学校所有学生到校离校情况
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@PostMapping(value = "studentAttendance_findtBySchool/{school_id}/{date}")
	@ApiOperation(value = "园长角色，查询某个学校所有学生到校离校情况", notes = "园长角色，查询某个学校所有学生到校离校情况")
	@ResponseBody
	public Out<OutList<Hand_clasStudentArriveAndLeave>> studentAttendance_findtBySchool(@PathVariable("school_id") Integer school_id,@PathVariable("date") String date) {
		logger.info("访问  AttendanceController:studentAttendance_findtBySchool,school_id=" + school_id);	
		Hand_attendanceData attendanceData=null;
        List<Hand_clasStudentArriveAndLeave> studentInfos=null;
        Hand_clasStudentArriveAndLeave info = null;
        List<Hand_studentArriveAndLeaveDate> studentDate = null;
        Map<Integer,List<Hand_studentArriveAndLeaveDate>> map = new HashMap<Integer,List<Hand_studentArriveAndLeaveDate>>();
		OutList<Hand_clasStudentArriveAndLeave> outList = null;
		Out<OutList<Hand_clasStudentArriveAndLeave>> back = new Out<OutList<Hand_clasStudentArriveAndLeave>>();
		try {
			attendanceData = new Hand_attendanceData();
			info = new Hand_clasStudentArriveAndLeave();
			studentInfos = new ArrayList<Hand_clasStudentArriveAndLeave>();
			attendanceData.setSchool_id(school_id);
			attendanceData.setDate(date);
			//获取学生到校离校数据
			studentDate = attendanceService.findStudentArriveAndLeaveBySchool(attendanceData);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			for (int i = 0; i < studentDate.size(); i++) {
				//转换到校离校时间格式
				if(studentDate.get(i).getArriveTime()!=null){
					studentDate.get(i).setArrive(sdf.format(studentDate.get(i).getArriveTime()));;
				}else{
					studentDate.get(i).setArrive("未到校");
				}
				if(studentDate.get(i).getLeaveTime()!=null){
					studentDate.get(i).setLeave(sdf.format(studentDate.get(i).getLeaveTime()));
				}else{
					studentDate.get(i).setLeave("未离校");
				}
				 int class_id = studentDate.get(i).getClass_id();
				 //按班级id组装map
				if(map.containsKey(class_id)){
					map.get(class_id).add(studentDate.get(i));
				}else{
					List<Hand_studentArriveAndLeaveDate> mapDate =  new ArrayList<Hand_studentArriveAndLeaveDate>();
					mapDate.add(studentDate.get(i));
					map.put(class_id, mapDate);
				}
			}
			//循环map按班级分类学生
			Set<Integer> keys=map.keySet();
			Iterator<Integer> it = keys.iterator();
			while(it.hasNext()){
				int key = it.next();
				info.setClass_id(key);
				info.setStudentDate(map.get(key));
				info.setTitle(map.get(key).get(0).getTitle());
				studentInfos.add(info);
			}
			outList = new OutList<Hand_clasStudentArriveAndLeave>(studentInfos.size(), studentInfos);
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
