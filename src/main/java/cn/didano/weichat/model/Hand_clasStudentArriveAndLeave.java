package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

public class Hand_clasStudentArriveAndLeave {

	private int class_id;
	private String title;
	private List<Hand_studentArriveAndLeaveDate> studentDate = new ArrayList<Hand_studentArriveAndLeaveDate>();
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Hand_studentArriveAndLeaveDate> getStudentDate() {
		return studentDate;
	}
	public void setStudentDate(List<Hand_studentArriveAndLeaveDate> studentDate) {
		this.studentDate = studentDate;
	}
	
	
}
