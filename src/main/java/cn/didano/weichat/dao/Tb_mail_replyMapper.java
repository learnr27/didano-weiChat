package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_mail_reply;
import cn.didano.weichat.model.Tb_mail_replyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_mail_replyMapper {
    long countByExample(Tb_mail_replyExample example);

    int deleteByExample(Tb_mail_replyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_mail_reply record);

    int insertSelective(Tb_mail_reply record);

    List<Tb_mail_reply> selectByExample(Tb_mail_replyExample example);

    Tb_mail_reply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_mail_reply record, @Param("example") Tb_mail_replyExample example);

    int updateByExample(@Param("record") Tb_mail_reply record, @Param("example") Tb_mail_replyExample example);

    int updateByPrimaryKeySelective(Tb_mail_reply record);

    int updateByPrimaryKey(Tb_mail_reply record);
}