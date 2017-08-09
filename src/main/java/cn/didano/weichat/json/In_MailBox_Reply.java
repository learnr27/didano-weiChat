package cn.didano.weichat.json;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class In_MailBox_Reply {


	@ApiModelProperty(value = "内容",required=true)
	private String content;
	@ApiModelProperty(value = "登录者id",required=true)
	private Integer userId;
	@ApiModelProperty(value = "学生id,登录者为园长时不填",required=true)
	private Integer studentId;
	@ApiModelProperty(value = "登录者类型 30家长 31园长，当通知id为0时使用",required=true)
	private byte userType;
	@ApiModelProperty(value = "通知id,仅回复时使用",required=true)
	private Integer mailId;
	
	
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public byte getUserType() {
		return userType;
	}
	public void setUserType(byte userType) {
		this.userType = userType;
	}
	
	public Integer getMailId() {
		return mailId;
	}
	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

	
}
