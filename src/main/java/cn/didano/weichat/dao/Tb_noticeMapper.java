package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Hand_addressName;
import cn.didano.weichat.model.Tb_notice;
import cn.didano.weichat.model.Tb_noticeExample;
import cn.didano.weichat.model.Tb_staff;

public interface Tb_noticeMapper {
    long countByExample(Tb_noticeExample example);

    int deleteByExample(Tb_noticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_notice record);

    int insertSelective(Tb_notice record);

    List<Tb_notice> selectByExample(Tb_noticeExample example);

    Tb_notice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_notice record, @Param("example") Tb_noticeExample example);

    int updateByExample(@Param("record") Tb_notice record, @Param("example") Tb_noticeExample example);

    int updateByPrimaryKeySelective(Tb_notice record);

    int updateByPrimaryKey(Tb_notice record);
    //手写
    List<Tb_staff> selectBossByParentId(Integer userId);
    List<Hand_addressName> selectAddressName(Integer userId);
}