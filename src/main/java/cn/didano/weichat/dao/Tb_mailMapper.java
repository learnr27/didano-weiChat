package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_mail;
import cn.didano.weichat.model.Tb_mailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_mailMapper {
    long countByExample(Tb_mailExample example);

    int deleteByExample(Tb_mailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_mail record);

    int insertSelective(Tb_mail record);

    List<Tb_mail> selectByExample(Tb_mailExample example);

    Tb_mail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_mail record, @Param("example") Tb_mailExample example);

    int updateByExample(@Param("record") Tb_mail record, @Param("example") Tb_mailExample example);

    int updateByPrimaryKeySelective(Tb_mail record);

    int updateByPrimaryKey(Tb_mail record);
}