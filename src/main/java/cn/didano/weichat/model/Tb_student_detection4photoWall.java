package cn.didano.weichat.model;

import java.util.Date;

public class Tb_student_detection4photoWall {
    private Integer id;

    private Integer studentId;

    private Integer height;

    private Integer weight;

    private Float temperature;

    private String orgImgUrl;

    private Date created;

    private String hobby;
    
    private String dream;
    
    public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getDream() {
		return dream;
	}

	public void setDream(String dream) {
		this.dream = dream;
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

   

  

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getOrgImgUrl() {
        return orgImgUrl;
    }

    public void setOrgImgUrl(String orgImgUrl) {
        this.orgImgUrl = orgImgUrl == null ? null : orgImgUrl.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}