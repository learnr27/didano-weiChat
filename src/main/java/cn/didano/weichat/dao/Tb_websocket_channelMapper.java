package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_websocket_channel;
import cn.didano.weichat.model.Tb_websocket_channelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_websocket_channelMapper {
    long countByExample(Tb_websocket_channelExample example);

    int deleteByExample(Tb_websocket_channelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_websocket_channel record);

    int insertSelective(Tb_websocket_channel record);

    List<Tb_websocket_channel> selectByExample(Tb_websocket_channelExample example);

    Tb_websocket_channel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_websocket_channel record, @Param("example") Tb_websocket_channelExample example);

    int updateByExample(@Param("record") Tb_websocket_channel record, @Param("example") Tb_websocket_channelExample example);

    int updateByPrimaryKeySelective(Tb_websocket_channel record);

    int updateByPrimaryKey(Tb_websocket_channel record);
}