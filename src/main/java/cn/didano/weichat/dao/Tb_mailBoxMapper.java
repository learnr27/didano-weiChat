package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Tb_mailBox;
import cn.didano.weichat.model.Tb_mailBoxExample;
import cn.didano.weichat.model.Tb_staff;

public interface Tb_mailBoxMapper {
    long countByExample(Tb_mailBoxExample example);

    int deleteByExample(Tb_mailBoxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_mailBox record);

    int insertSelective(Tb_mailBox record);

    List<Tb_mailBox> selectByExample(Tb_mailBoxExample example);

    Tb_mailBox selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_mailBox record, @Param("example") Tb_mailBoxExample example);

    int updateByExample(@Param("record") Tb_mailBox record, @Param("example") Tb_mailBoxExample example);

    int updateByPrimaryKeySelective(Tb_mailBox record);

    int updateByPrimaryKey(Tb_mailBox record);
    
    //手写
    List<Tb_staff> selectBossByParentId(Integer userId);
}