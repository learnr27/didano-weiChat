package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_notice_reply;
import cn.didano.weichat.model.Tb_notice_replyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_notice_replyMapper {
    long countByExample(Tb_notice_replyExample example);

    int deleteByExample(Tb_notice_replyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_notice_reply record);

    int insertSelective(Tb_notice_reply record);

    List<Tb_notice_reply> selectByExample(Tb_notice_replyExample example);

    Tb_notice_reply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_notice_reply record, @Param("example") Tb_notice_replyExample example);

    int updateByExample(@Param("record") Tb_notice_reply record, @Param("example") Tb_notice_replyExample example);

    int updateByPrimaryKeySelective(Tb_notice_reply record);

    int updateByPrimaryKey(Tb_notice_reply record);
}