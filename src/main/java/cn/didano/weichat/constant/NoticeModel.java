package cn.didano.weichat.constant;

/**
 * 人类型
 * 
 * @author stephen Created on 2016年12月20日 下午1:16:00
 */
public enum NoticeModel {
	SIMPLE_MODEL("简单模型", (byte)0), 
	INSIDE_URL("内部url", (byte)1),
	OUTSIDE_URL("外部url", (byte)2);

	
	private String name;
	private byte index;

	private NoticeModel(String name, byte index) {
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
