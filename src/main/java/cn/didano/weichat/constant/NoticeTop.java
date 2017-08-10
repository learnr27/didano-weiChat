package cn.didano.weichat.constant;

/**
 * 人类型
 * 
 * @author stephen Created on 2016年12月20日 下午1:16:00
 */
public enum NoticeTop {
	NOT_TOP("不置顶", (byte)0), 
	TOP("置顶", (byte)1);

	
	private String name;
	private byte index;

	private NoticeTop(String name, byte index) {
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
