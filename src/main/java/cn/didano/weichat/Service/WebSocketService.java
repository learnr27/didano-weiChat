package cn.didano.weichat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Tb_websocket__channelMapper;
import cn.didano.weichat.model.Tb_websocket__channel;
import cn.didano.weichat.model.Tb_websocket__channelExample;

@Service
public class WebSocketService {

	@Autowired
	private Tb_websocket__channelMapper webSocketMapper;
	
	/**
	 * 根据频道类型查询频道
	 */
	public List<Tb_websocket__channel> selcetChannelByType(byte type){
		Tb_websocket__channelExample condition = new Tb_websocket__channelExample();
		Tb_websocket__channelExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示 禁用不显示
		criteria.andDeletedEqualTo(false);
		criteria.andChannelTypeEqualTo(type);
		return webSocketMapper.selectByExample(condition);
	
	}
}
