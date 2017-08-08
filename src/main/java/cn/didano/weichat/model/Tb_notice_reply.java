package cn.didano.weichat.model;

import java.util.Date;

public class Tb_notice_reply {
    private Integer id;

    private String content;

    private Integer noticeid;

    private Integer addresserid;

    private String addressername;

    private Date created;

    private Date updated;

    private Boolean deleted;
    
    //手写
    private String date;
    private String head;
    

    public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public Integer getAddresserid() {
        return addresserid;
    }

    public void setAddresserid(Integer addresserid) {
        this.addresserid = addresserid;
    }

    public String getAddressername() {
        return addressername;
    }

    public void setAddressername(String addressername) {
        this.addressername = addressername == null ? null : addressername.trim();
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