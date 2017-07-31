package cn.didano.weichat.json;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class In_Notice_Edit {

	@ApiModelProperty(value = "标题",required=true)
	private String title ;
	@ApiModelProperty(value = "内容",required=true)
	private String content;
	@ApiModelProperty(value = "优先级 0 -普通 1-置顶",required=true)
	private Byte priority;
	@ApiModelProperty(value = "通知类型 0-晨检报告 1-园长通知 2-接送报告 3-公众号 4-其他",required=true)
	private Byte noticeType;
	@ApiModelProperty(value = "登录者id",required=true)
	private Integer onlineId;
	@ApiModelProperty(value = "接收者id",required=true)
	private List<Integer> userId = new ArrayList<Integer>();
	@ApiModelProperty(value = "用户类型 ",required=true)
	private Byte user_type;
	
	public Integer getOnlineId() {
		return onlineId;
	}
	public void setOnlineId(Integer onlineId) {
		this.onlineId = onlineId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Byte getPriority() {
		return priority;
	}
	public void setPriority(Byte priority) {
		this.priority = priority;
	}
	
	public Byte getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(Byte noticeType) {
		this.noticeType = noticeType;
	}
	public List<Integer> getUserId() {
		return userId;
	}
	public void setUserId(List<Integer> userId) {
		this.userId = userId;
	}
	public Byte getUser_type() {
		return user_type;
	}
	public void setUser_type(Byte user_type) {
		this.user_type = user_type;
	}
	

	
}
