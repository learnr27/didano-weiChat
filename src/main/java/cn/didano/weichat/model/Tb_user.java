package cn.didano.weichat.model;

import java.util.Date;

public class Tb_user {
    private Integer id;

    private String openid;

    private String officialaccountid;

    private String nickname;

    private String phone;

    private Date created;

    private Date updated;

    private Integer type;

    private Boolean deleted;

    private Integer schoolParentId;

    private Integer studentId;

    private Integer staffId;

    private Integer adminId;

    private Integer schoolAdminId;

    private String description;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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