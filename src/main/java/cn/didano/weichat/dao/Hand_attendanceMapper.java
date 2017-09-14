package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Hand_attendanceData;
import cn.didano.weichat.model.Hand_classArriveAndLeaveInfo;
import cn.didano.weichat.model.Hand_studentArriveAndLeaveDate;
import cn.didano.weichat.model.Hand_teacherAttendance;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_staff_signdate;
import cn.didano.weichat.model.Tb_student_detection;

public interface Hand_attendanceMapper {
	int getDayArriveSchoolStatistic(Hand_attendanceData data);
	int getDayLeaveSchoolStatistic(Hand_attendanceData data);
	int getSchoolAllStudentNum(Integer schoolId);
	int getSchoolAllStaffNum(Integer schoolId);
	int getSchoolArriveStaffNum(Hand_attendanceData data);
	List<Hand_classArriveAndLeaveInfo> getClassArriveSchoolNumList(Hand_attendanceData data);
	List<Hand_classArriveAndLeaveInfo> getClassLeaveSchoolNumList(Hand_attendanceData data);
    List<Tb_student_detection>  getTeacherClassArriveSchoolList(String date);
    List<Tb_student_detection>  getTeacherClassLeaveSchoolList(String date);
    List<Tb_student_detection>  getClassArriveStudentList(String date);
    List<Tb_student_detection>  getClassLeaveStudentList(Hand_attendanceData data);
    List<Tb_student_detection>  getSchoolTemperatureStatisticList(Hand_attendanceData data);
    int getStudentsNum(Integer class_id);
    List<Tb_staff> getDaySignStatisticList(Hand_attendanceData data);
    List<Tb_staff_signdate> getMonthSignStatisticList(Hand_attendanceData data);
    Tb_staff findStaffInfoById(Integer id);
    List<Hand_studentArriveAndLeaveDate> findStudentArriveAndLeaveByClass(Hand_attendanceData data);
    List<Hand_studentArriveAndLeaveDate> findStudentArriveAndLeaveBySchool(Hand_attendanceData data);
    List<Hand_teacherAttendance> findTeacherMonthAttendanceById(Hand_attendanceData data);
}