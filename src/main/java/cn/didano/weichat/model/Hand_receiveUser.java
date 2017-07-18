package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

public class Hand_receiveUser {

	private List<Integer> schoolAllPeople = new ArrayList<Integer>();
	private List<Integer> schoolAllStaff  = new ArrayList<Integer>();
	private List<Integer> schoolAllParent  = new ArrayList<Integer>();
	private List<Hand_classReceiveUser> classStaffAndParent = new ArrayList<Hand_classReceiveUser>();
	public List<Integer> getSchoolAllPeople() {
		return schoolAllPeople;
	}
	public void setSchoolAllPeople(List<Integer> schoolAllPeople) {
		this.schoolAllPeople = schoolAllPeople;
	}
	public List<Integer> getSchoolAllStaff() {
		return schoolAllStaff;
	}
	public void setSchoolAllStaff(List<Integer> schoolAllStaff) {
		this.schoolAllStaff = schoolAllStaff;
	}
	public List<Integer> getSchoolAllParent() {
		return schoolAllParent;
	}
	public void setSchoolAllParent(List<Integer> schoolAllParent) {
		this.schoolAllParent = schoolAllParent;
	}
	public List<Hand_classReceiveUser> getClassStaffAndParent() {
		return classStaffAndParent;
	}
	public void setClassStaffAndParent(List<Hand_classReceiveUser> classStaffAndParent) {
		this.classStaffAndParent = classStaffAndParent;
	}
	
}
