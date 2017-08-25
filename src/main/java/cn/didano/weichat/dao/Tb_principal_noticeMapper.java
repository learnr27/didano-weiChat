package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_principal_notice;
import cn.didano.weichat.model.Tb_principal_noticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_principal_noticeMapper {
    long countByExample(Tb_principal_noticeExample example);

    int deleteByExample(Tb_principal_noticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_principal_notice record);

    int insertSelective(Tb_principal_notice record);

    List<Tb_principal_notice> selectByExample(Tb_principal_noticeExample example);

    Tb_principal_notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_principal_notice record, @Param("example") Tb_principal_noticeExample example);

    int updateByExample(@Param("record") Tb_principal_notice record, @Param("example") Tb_principal_noticeExample example);

    int updateByPrimaryKeySelective(Tb_principal_notice record);

    int updateByPrimaryKey(Tb_principal_notice record);
}