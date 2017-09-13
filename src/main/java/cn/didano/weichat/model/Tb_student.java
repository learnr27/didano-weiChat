package cn.didano.weichat.model;

import java.util.Date;

public class Tb_student {
    private Integer id;

    private Integer schoolId;

    private Integer classId;

    private String name;

    private String nickname;

    private String hobby;

    private Integer dreamId;

    private String comment;

    private Byte gender;

    private Date birthday;

    private Byte deviceRegistered;

    private Byte status;

    private String rfid;

    private Integer icCardId;

    private Date created;

    private Date updated;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public Integer getDreamId() {
        return dreamId;
    }

    public void setDreamId(Integer dreamId) {
        this.dreamId = dreamId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Byte getDeviceRegistered() {
        return deviceRegistered;
    }

    public void setDeviceRegistered(Byte deviceRegistered) {
        this.deviceRegistered = deviceRegistered;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public Integer getIcCardId() {
        return icCardId;
    }

    public void setIcCardId(Integer icCardId) {
        this.icCardId = icCardId;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

	@Override
	public String toString() {
		return "Tb_student [id=" + id + ", schoolId=" + schoolId + ", classId=" + classId + ", name=" + name
				+ ", nickname=" + nickname + ", hobby=" + hobby + ", dreamId=" + dreamId + ", comment=" + comment
				+ ", gender=" + gender + ", birthday=" + birthday + ", deviceRegistered=" + deviceRegistered
				+ ", status=" + status + ", rfid=" + rfid + ", icCardId=" + icCardId + ", created=" + created
				+ ", updated=" + updated + ", deleted=" + deleted + "]";
	}
    
}