package cn.didano.weichat.constant;

/**
 * 图片归属模块路径类
 * @author wangyi
 *
 */
public enum ImageModuleType {
	NOTICE("园长通知", "deliverNotice/"),
	MESSAGE("消息", "message/");
	private String name;
	private String path;

	private ImageModuleType(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
