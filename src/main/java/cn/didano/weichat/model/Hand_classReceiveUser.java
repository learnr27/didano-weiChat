package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

public class Hand_classReceiveUser {

	private String className;
	private List<Integer> staffAndParent = new ArrayList<Integer>();
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<Integer> getStaffAndParent() {
		return staffAndParent;
	}
	public void setStaffAndParent(List<Integer> staffAndParent) {
		this.staffAndParent = staffAndParent;
	}
	
}
