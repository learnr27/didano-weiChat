package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Hand_attendanceMapper;
import cn.didano.weichat.model.Hand_attendanceData;
import cn.didano.weichat.model.Hand_classArriveAndLeaveInfo;
import cn.didano.weichat.model.Hand_studentArriveAndLeaveDate;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_staff_signdate;

@Service
public class AttendanceService {

	@Autowired
	private Hand_attendanceMapper attendanceMapper;
	
	/**
	 * 根据班级查找该学校应到人数
	 */
	public int findAllStudentNumByClass(Integer class_id){
		return attendanceMapper.getStudentsNum(class_id);
	}
	
	/**
	 * 每日班级到校人数统计
	 */
	public List<Hand_classArriveAndLeaveInfo> findClassArriveNumBySchool(Hand_attendanceData data){
		return attendanceMapper.getClassArriveSchoolNumList(data);
	}
	
	/**
	 * 查询员工一个月内的考勤情况
	 */
	public List<Tb_staff_signdate> getStaffMonthSignStatisticListById(Hand_attendanceData data){
		return attendanceMapper.getMonthSignStatisticList(data);
	}
	/**
	 * 根据员工id查询员工信息
	 */
	public Tb_staff findStaffInfoById(Integer id){
		return attendanceMapper.findStaffInfoById(id);
	}
	/**
	 * 根据班级id查找该班级学生到校离校情况
	 */
	public List<Hand_studentArriveAndLeaveDate> findStudentArriveAndLeaveByClass(Integer class_id){
		return attendanceMapper.findStudentArriveAndLeaveByClass(class_id);
	}
}
