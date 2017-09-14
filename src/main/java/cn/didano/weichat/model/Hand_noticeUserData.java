package cn.didano.weichat.model;

public class Hand_noticeUserData {

	private Integer userId;
	private byte userType;
	private byte noticeType;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public byte getUserType() {
		return userType;
	}
	public void setUserType(byte userType) {
		this.userType = userType;
	}
	public byte getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(byte noticeType) {
		this.noticeType = noticeType;
	}
	
}
