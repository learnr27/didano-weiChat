package cn.didano.weichat.json;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class In_MailBox_Edit {


	@ApiModelProperty(value = "内容",required=true)
	private String content;
	@ApiModelProperty(value = "登录者id",required=true)
	private Integer userId;
	@ApiModelProperty(value = "通知id,仅回复时使用",required=true)
	private Integer noticeId;
	
	
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
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
