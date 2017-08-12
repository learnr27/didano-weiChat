package cn.didano.weichat.model;

import java.util.HashSet;
import java.util.Set;

public class Tb_user {
	private Integer id;

	private String openid;

	private String officialaccountid;

	private String nickname;

	private String mobile;

	private String station;

	private String createDate;

	private String updateDate;

	private Integer type;

	private Boolean deleteFlag;

	private Integer schoolParentId;

	private Integer studentId;

	private Integer staffId;

	private Integer adminId;

	private Integer schoolAdminId;

	private String description;

	private Set<Tb_role> roles = new HashSet<>(0);

	public Set<Tb_role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Tb_role> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getOfficialaccountid() {
		return officialaccountid;
	}

	public void setOfficialaccountid(String officialaccountid) {
		this.officialaccountid = officialaccountid == null ? null : officialaccountid.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station == null ? null : station.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate == null ? null : createDate.trim();
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate == null ? null : updateDate.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getSchoolParentId() {
		return schoolParentId;
	}

	public void setSchoolParentId(Integer schoolParentId) {
		this.schoolParentId = schoolParentId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getSchoolAdminId() {
		return schoolAdminId;
	}

	public void setSchoolAdminId(Integer schoolAdminId) {
		this.schoolAdminId = schoolAdminId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}
}