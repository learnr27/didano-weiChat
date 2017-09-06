package cn.didano.weichat.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Tb_student_away_record {
    private Integer id;

    private Integer studentId;

    private Integer parentId;

    private String img;

    private Integer fileImgId;

    private String subImg;

    private Integer fileSubImgId;

    private Date created;
    //手写
    private String type;
    private String parentName;
    

    public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getFileImgId() {
        return fileImgId;
    }

    public void setFileImgId(Integer fileImgId) {
        this.fileImgId = fileImgId;
    }

    public String getSubImg() {
        return subImg;
    }

    public void setSubImg(String subImg) {
        this.subImg = subImg == null ? null : subImg.trim();
    }

    public Integer getFileSubImgId() {
        return fileSubImgId;
    }

    public void setFileSubImgId(Integer fileSubImgId) {
        this.fileSubImgId = fileSubImgId;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}