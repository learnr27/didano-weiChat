package cn.didano.weichat.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class In_Read_Date {

	
	@ApiModelProperty(value = "用户id",required=true)
	private int userId ;
	@ApiModelProperty(value = "通知id",required=true)
	private int noticeId ;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	
}
