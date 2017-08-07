package cn.didano.weichat.repository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public final class WxMpInMemoryConfigStorageContainer {
	private static Logger logger = LoggerFactory.getLogger(WxMpInMemoryConfigStorageContainer.class);
	private static HashMap<String, WxMpInMemoryConfigStorage> container = new HashMap<String, WxMpInMemoryConfigStorage>();

	public static WxMpInMemoryConfigStorage findByOriId(String OriId) {
		logger.info("findByOriId...OriId数据为:" + OriId);
		WxMpInMemoryConfigStorage target = container.get(OriId);
		return target;
	}

	public static void save(String oriId,WxMpInMemoryConfigStorage one) {
		container.put(oriId, one);
	}
}