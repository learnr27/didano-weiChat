package cn.didano.weichat.json;

import cn.didano.weichat.model.Tb_student;

public class Out_Student_Search extends Tb_student {

	private Integer id;				//用户id;
	
	private Integer studentId;		//学生id;
	
	private String schooltitle;

	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getSchooltitle() {
		return schooltitle;
	}

	public void setSchooltitle(String schooltitle) {
		this.schooltitle = schooltitle;
	}



}
