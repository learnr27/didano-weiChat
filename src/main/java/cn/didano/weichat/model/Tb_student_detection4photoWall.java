package cn.didano.weichat.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Tb_student_detection4photoWall {
    private Integer id;

    private Integer studentId;

    private Float height;

    private Float weight;

    private Float temperature;

    private String orgImgUrl;
    
    private String filePath;

    private Date created;

    private String hobby;
    
    private String dream;
    
    
    public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

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

   

  

	

    public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	@Override
	public String toString() {
		return "Tb_student_detection4photoWall [id=" + id + ", studentId=" + studentId + ", height=" + height
				+ ", weight=" + weight + ", temperature=" + temperature + ", orgImgUrl=" + orgImgUrl + ", filePath="
				+ filePath + ", created=" + created + ", hobby=" + hobby + ", dream=" + dream + "]";
	}
    
}