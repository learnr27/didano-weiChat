package cn.didano.weichat.model;

import java.util.Date;

public class Tb_staff {
    private Integer id;

    private Byte type;

    private Integer school_id;

    private String name;

    private String phone;

    private String rfid;

    private Integer icCardId;

    private String qrcodeUrl;

    private String qrcodeUrlOld;

    private String qrcodeSchoolKey;

    private String qrcodeSchoolKeyOld;

    private Integer signTypeId;

    private Byte status;

    private Date created;

    private Boolean deleted;

    private Date updated;
    //手写
    private String title;
    
    private Integer class_id;
    
    private byte sign_status;
    
    private Date in_time;
    
    private Date out_time;
    
   
    public Integer getSchool_id() {
		return school_id;
	}

	public void setSchool_id(Integer school_id) {
		this.school_id = school_id;
	}

	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getSign_status() {
		return sign_status;
	}

	public void setSign_status(byte sign_status) {
		this.sign_status = sign_status;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
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

    public Integer getIcCardId() {
        return icCardId;
    }

    public void setIcCardId(Integer icCardId) {
        this.icCardId = icCardId;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}