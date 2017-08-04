package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_websocket__channel;
import cn.didano.weichat.model.Tb_websocket__channelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_websocket__channelMapper {
    long countByExample(Tb_websocket__channelExample example);

    int deleteByExample(Tb_websocket__channelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_websocket__channel record);

    int insertSelective(Tb_websocket__channel record);

    List<Tb_websocket__channel> selectByExample(Tb_websocket__channelExample example);

    Tb_websocket__channel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_websocket__channel record, @Param("example") Tb_websocket__channelExample example);

    int updateByExample(@Param("record") Tb_websocket__channel record, @Param("example") Tb_websocket__channelExample example);

    int updateByPrimaryKeySelective(Tb_websocket__channel record);

    int updateByPrimaryKey(Tb_websocket__channel record);
}