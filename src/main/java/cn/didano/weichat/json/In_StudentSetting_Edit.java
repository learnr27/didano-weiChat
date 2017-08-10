package cn.didano.weichat.json;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class In_StudentSetting_Edit {

	@ApiModelProperty(value = "家长id",required=true)
	private Integer parentId ;
	@ApiModelProperty(value = "学生id",required=true)
	private Integer studentId ;
	@ApiModelProperty(value = "小名",required=true)
	private String nickname;

	@ApiModelProperty(value = "性别",required=true)
	private Byte gender;
	@ApiModelProperty(value = "生日",required=true)
	private Date birthday;
	@ApiModelProperty(value = "梦想id",required=true)
	private int dreamId;
	@ApiModelProperty(value = "爱好",required=true)
	private String hobby;
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public int getDreamId() {
		return dreamId;
	}
	public void setDreamId(int dreamId) {
		this.dreamId = dreamId;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	

	
}
