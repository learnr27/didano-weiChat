package cn.didano.weichat.json;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class In_MailBox_Write {


	@ApiModelProperty(value = "内容",required=true)
	private String content;
	@ApiModelProperty(value = "登录者id",required=true)
	private Integer userId;

	
	
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
