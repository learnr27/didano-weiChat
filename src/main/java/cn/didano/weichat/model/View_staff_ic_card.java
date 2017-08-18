package cn.didano.weichat.model;

import java.util.Date;

public class View_staff_ic_card {
    private String icNumber;

    private Integer id;

    private Byte type;

    private Integer schoolId;

    private String name;

    private String phone;

    private String rfid;

    private String qrcodeUrl;

    private String qrcodeUrlOld;

    private String qrcodeSchoolKey;

    private String qrcodeSchoolKeyOld;

    private Integer signTypeId;

    private Byte status;

    private Date created;

    private Date updated;

    private Boolean deleted;

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber == null ? null : icNumber.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl == null ? null : qrcodeUrl.trim();
    }

    public String getQrcodeUrlOld() {
        return qrcodeUrlOld;
    }

    public void setQrcodeUrlOld(String qrcodeUrlOld) {
        this.qrcodeUrlOld = qrcodeUrlOld == null ? null : qrcodeUrlOld.trim();
    }

    public String getQrcodeSchoolKey() {
        return qrcodeSchoolKey;
    }

    public void setQrcodeSchoolKey(String qrcodeSchoolKey) {
        this.qrcodeSchoolKey = qrcodeSchoolKey == null ? null : qrcodeSchoolKey.trim();
    }

    public String getQrcodeSchoolKeyOld() {
        return qrcodeSchoolKeyOld;
    }

    public void setQrcodeSchoolKeyOld(String qrcodeSchoolKeyOld) {
        this.qrcodeSchoolKeyOld = qrcodeSchoolKeyOld == null ? null : qrcodeSchoolKeyOld.trim();
    }

    public Integer getSignTypeId() {
        return signTypeId;
    }

    public void setSignTypeId(Integer signTypeId) {
        this.signTypeId = signTypeId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}