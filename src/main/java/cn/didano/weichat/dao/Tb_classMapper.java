package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Tb_class;
import cn.didano.weichat.model.Tb_classExample;

public interface Tb_classMapper {
    long countByExample(Tb_classExample example);

    int deleteByExample(Tb_classExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_class record);

    int insertSelective(Tb_class record);

    List<Tb_class> selectByExample(Tb_classExample example);

    Tb_class selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_class record, @Param("example") Tb_classExample example);

    int updateByExample(@Param("record") Tb_class record, @Param("example") Tb_classExample example);

    int updateByPrimaryKeySelective(Tb_class record);

    int updateByPrimaryKey(Tb_class record);
}