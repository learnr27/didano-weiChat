package cn.didano.weichat.json;

import java.util.ArrayList;
import java.util.List;

import cn.didano.weichat.model.Hand_user;
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
//	@ApiModelProperty(value = "通知类型 0-晨检报告 1-园长通知 2-接送报告 3-公众号 4-园长信箱",required=true)
//	private Byte noticeType;
	@ApiModelProperty(value = "消息模型 0-简单模型 1-内部url 2-外部url",required=true)
	private Byte noticeModel;
	@ApiModelProperty(value = "url路径，当消息模型为2时使用",required=true)
	private String url;
	@ApiModelProperty(value = "登录者id",required=true)
	private Integer onlineId;
	@ApiModelProperty(value = "接收班级或者学生id",required=true)
	private List<Integer> classIdOrStudentId= new ArrayList<Integer>();
	@ApiModelProperty(value = "发布是否勾选仅教职工",required=true)
	private byte allStaff;

	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Byte getNoticeModel() {
		return noticeModel;
	}
	public void setNoticeModel(Byte noticeModel) {
		this.noticeModel = noticeModel;
	}
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
	
//	public Byte getNoticeType() {
//		return noticeType;
//	}
//	public void setNoticeType(Byte noticeType) {
//		this.noticeType = noticeType;
//	}
	
	public byte getAllStaff() {
		return allStaff;
	}
	public List<Integer> getClassIdOrStudentId() {
		return classIdOrStudentId;
	}
	public void setClassIdOrStudentId(List<Integer> classIdOrStudentId) {
		this.classIdOrStudentId = classIdOrStudentId;
	}
	public void setAllStaff(byte allStaff) {
		this.allStaff = allStaff;
	}
	


	
}
