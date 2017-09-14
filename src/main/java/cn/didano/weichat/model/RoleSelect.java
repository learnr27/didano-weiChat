package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

import cn.didano.weichat.json.Out_Student_Search;

public class RoleSelect {

	private List<Tb_role> roleList = new ArrayList<Tb_role>();
	
	private List<Tb_school> schoolList = new ArrayList<Tb_school>();
	
	private List<Out_Student_Search> studentList = new ArrayList<Out_Student_Search>();

	public List<Tb_role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Tb_role> roleList) {
		this.roleList = roleList;
	}

	public List<Tb_school> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(List<Tb_school> schoolList) {
		this.schoolList = schoolList;
	}

	public List<Out_Student_Search> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Out_Student_Search> studentList) {
		this.studentList = studentList;
	}

	
	
	
}
