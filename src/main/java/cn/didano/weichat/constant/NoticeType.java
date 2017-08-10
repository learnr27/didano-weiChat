package cn.didano.weichat.constant;

/**
 * 人类型
 * 
 * @author stephen Created on 2016年12月20日 下午1:16:00
 */
public enum NoticeType {
	MORNING_REPORT("晨检报告", (byte)0), 
	PRINCIPAL_NOTICE("园长通知", (byte)1),
	SHUTTLE_REPORT("接送报告", (byte)2),
	PUBLIC_SIGNAL("公众号", (byte)3),
	PRINCIPAL_MAIL("园长信箱", (byte)4);
	

	
	private String name;
	private byte index;

	private NoticeType(String name, byte index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getIndex() {
		return index;
	}

	public void setIndex(byte index) {
		this.index = index;
	}
}
