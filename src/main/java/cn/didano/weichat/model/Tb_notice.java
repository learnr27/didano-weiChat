package cn.didano.weichat.model;

import java.util.Date;

public class Tb_notice {
    private Integer id;

    private String title;

    private String content;

    private Byte priority;

    private Integer senderId;

    private String senderName;

    private Byte noticeModel;

    private String redirectUrl;

    private Integer sourceId;

    private String publicationScope;

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

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public Byte getNoticeModel() {
        return noticeModel;
    }

    public void setNoticeModel(Byte noticeModel) {
        this.noticeModel = noticeModel;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getPublicationScope() {
        return publicationScope;
    }

    public void setPublicationScope(String publicationScope) {
        this.publicationScope = publicationScope == null ? null : publicationScope.trim();
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

	@Override
	public String toString() {
		return " created=" + created + ", updated=" + updated + ", deleted="
				+ deleted + ", is_read=" + is_read + ", headUrl=" + headUrl + ", date=" + date + "]";
	}
    
}