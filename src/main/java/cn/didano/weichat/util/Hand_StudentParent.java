package cn.didano.weichat.util;

import cn.didano.weichat.model.Tb_school_parent;

public class Hand_StudentParent extends Tb_school_parent {

	private Integer parentId;
	private Integer studentId;
	private String parentName;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
