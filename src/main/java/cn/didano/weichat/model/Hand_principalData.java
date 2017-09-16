package cn.didano.weichat.model;

import java.util.List;

public class Hand_principalData {
	private List<Tb_principal_notice> principalNotices;
	private List<String> readerNames;
	private Integer readerNum;
	private Integer allParent;
	private Integer readParent;
	private Integer allTeacher;
	private Integer readTeacher;
	
	public Integer getAllParent() {
		return allParent;
	}
	public void setAllParent(Integer allParent) {
		this.allParent = allParent;
	}
	public Integer getReadParent() {
		return readParent;
	}
	public void setReadParent(Integer readParent) {
		this.readParent = readParent;
	}
	public Integer getAllTeacher() {
		return allTeacher;
	}
	public void setAllTeacher(Integer allTeacher) {
		this.allTeacher = allTeacher;
	}
	public Integer getReadTeacher() {
		return readTeacher;
	}
	public void setReadTeacher(Integer readTeacher) {
		this.readTeacher = readTeacher;
	}
	public Integer getReaderNum() {
		return readerNum;
	}
	public void setReaderNum(Integer readerNum) {
		this.readerNum = readerNum;
	}
	public List<Tb_principal_notice> getPrincipalNotices() {
		return principalNotices;
	}
	public void setPrincipalNotices(List<Tb_principal_notice> principalNotices) {
		this.principalNotices = principalNotices;
	}
	public List<String> getReaderNames() {
		return readerNames;
	}
	public void setReaderNames(List<String> readerNames) {
		this.readerNames = readerNames;
	}
	

}
