package cn.didano.weichat.json;

import cn.didano.weichat.model.Tb_student;

public class Out_Student_Search extends Tb_student {

	private Integer relation_id;
	
	private String relation_title;
	
	private String classtitle;
	
	private String schooltitle;
	
	private String parent_id;
	
	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getClasstitle() {
		return classtitle;
	}

	public void setClasstitle(String classtitle) {
		this.classtitle = classtitle;
	}

	public String getSchooltitle() {
		return schooltitle;
	}

	public void setSchooltitle(String schooltitle) {
		this.schooltitle = schooltitle;
	}

	public Integer getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}

	public String getRelation_title() {
		return relation_title;
	}

	public void setRelation_title(String relation_title) {
		this.relation_title = relation_title;
	}

}
