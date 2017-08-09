package cn.didano.weichat.model;

import java.util.List;

/**
 * 角色信息 包含所有角色类型的相关属性
 * 
 * @author TOPFEEL
 *
 */
public class Hand_Role_Weichat {

	/*private String roleName;
	private int roleType;*/
	
	private String relation;			// 与学生关系名称，如:奶奶;
	private String parentId;
	private Integer staffId;			//员工id;
	private String studentName;
	private int studentId;
	private String className;
	private int classId;
	private String schoolName;
	private int schoolId;

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

}
