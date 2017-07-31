package cn.didano.weichat.model;

import java.util.Date;

public class Tb_mailBox {
    private Integer id;

    private String title;

    private String content;

    private Integer receiverid;

    private Byte receivertype;

    private Integer addresserid;

    private String addressername;

    private Date created;

    private Date updated;

    private Boolean deleted;

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

    public Integer getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(Integer receiverid) {
        this.receiverid = receiverid;
    }

    public Byte getReceivertype() {
        return receivertype;
    }

    public void setReceivertype(Byte receivertype) {
        this.receivertype = receivertype;
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