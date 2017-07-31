package cn.didano.weichat.model;

import java.util.Date;

public class Tb_notice {
    private Integer id;

    private String title;

    private String content;

    private Byte priority;

    private Integer addresserId;

    private String addresserName;

    private Byte personType;

    private Byte noticeType;

    private Date created;

    private Date updated;

    private Boolean deleted;

  //手写加入
    private byte is_read;
    
    private String headUrl;
    
    private String date;
    
    public byte getIs_read() {
		return is_read;
	}

	public void setIs_read(byte is_read) {
		this.is_read = is_read;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Integer getAddresserId() {
        return addresserId;
    }

    public void setAddresserId(Integer addresserId) {
        this.addresserId = addresserId;
    }

    public String getAddresserName() {
        return addresserName;
    }

    public void setAddresserName(String addresserName) {
        this.addresserName = addresserName == null ? null : addresserName.trim();
    }

    public Byte getPersonType() {
        return personType;
    }

    public void setPersonType(Byte personType) {
        this.personType = personType;
    }

    public Byte getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Byte noticeType) {
        this.noticeType = noticeType;
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