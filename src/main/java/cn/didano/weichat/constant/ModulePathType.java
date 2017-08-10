package cn.didano.weichat.constant;

/**
 * 人类型
 * 
 * @author stephen Created on 2016年12月20日 下午1:16:00
 */
public enum ModulePathType {
	MORNING_REPORT("晨检报告", "/base/mailbox/post/findReply_ByNoticeId/"),
	PRINCIPAL_NOTICE("园长通知", "/base/mailbox/post/findReply_ByNoticeId/"),
	SHUTTLE_REPORT("接送报告", "/base/mailbox/post/findReply_ByNoticeId/"),
	PUBLIC_SIGNAL("公众号", "/base/mailbox/post/findReply_ByNoticeId/"),
	PRINCIPAL_MAIL("园长信箱", "/base/mailbox/post/findReply_ByNoticeId/");

	
	private String name;
	private String url;

	private ModulePathType(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
