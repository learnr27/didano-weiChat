package cn.didano.weichat.repository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.didano.weichat.model.Tb_head_sculpture;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;

public final class HeadMemoryConfigStorageContainer {
	private static Logger logger = LoggerFactory.getLogger(HeadMemoryConfigStorageContainer.class);
	private static HashMap<Integer, Tb_head_sculpture> container = new HashMap<Integer, Tb_head_sculpture>();

	public static Tb_head_sculpture findByOriId(Integer headId) {
		logger.info("查找头像:" + headId);
		Tb_head_sculpture target = container.get(headId);
		return target;
	}

	public static void save(Integer oriId,Tb_head_sculpture one) {
		container.put(oriId, one);
	}
}