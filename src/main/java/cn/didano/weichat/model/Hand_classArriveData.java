package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

public class Hand_classArriveData {

	private Integer allNum;
	private Integer arriveNum;
	private Integer noArriveNum;
	private Integer classId;
	private List<Hand_teacherAttendance> teacherAttendance = new ArrayList<Hand_teacherAttendance>();
	
	public List<Hand_teacherAttendance> getTeacherAttendance() {
		return teacherAttendance;
	}
	public void setTeacherAttendance(List<Hand_teacherAttendance> teacherAttendance) {
		this.teacherAttendance = teacherAttendance;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getAllNum() {
		return allNum;
	}
	public void setAllNum(Integer allNum) {
		this.allNum = allNum;
	}
	public Integer getArriveNum() {
		return arriveNum;
	}
	public void setArriveNum(Integer arriveNum) {
		this.arriveNum = arriveNum;
	}
	public Integer getNoArriveNum() {
		return noArriveNum;
	}
	public void setNoArriveNum(Integer noArriveNum) {
		this.noArriveNum = noArriveNum;
	}
	@Override
	public String toString() {
		return "Hand_classArriveData [allNum=" + allNum + ", arriveNum=" + arriveNum + ", noArriveNum=" + noArriveNum
				+ ", classId=" + classId + "]";
	}
	
	
}
