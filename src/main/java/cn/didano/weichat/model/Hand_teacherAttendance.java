package cn.didano.weichat.model;

import java.util.Date;

public class Hand_teacherAttendance {

	private Integer staff_id;
	private Date created;
	private byte sign_status;
	private String sign_date;
	

	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getSign_date() {
		return sign_date;
	}
	public void setSign_date(String sign_date) {
		this.sign_date = sign_date;
	}
	public byte getSign_status() {
		return sign_status;
	}
	public void setSign_status(byte sign_status) {
		this.sign_status = sign_status;
	}
	
}
