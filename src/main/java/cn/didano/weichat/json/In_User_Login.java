package cn.didano.weichat.json;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class In_User_Login {
	
	@ApiModelProperty(value = "微信openid",required=true)
	private String openid ;
	@ApiModelProperty(value = "用户电话",required=true)
	private String mobile ;
	
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
