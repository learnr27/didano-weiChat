package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_school_parent;
import cn.didano.weichat.model.Tb_school_parentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_school_parentMapper {
    long countByExample(Tb_school_parentExample example);

    int deleteByExample(Tb_school_parentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_school_parent record);

    int insertSelective(Tb_school_parent record);

    List<Tb_school_parent> selectByExample(Tb_school_parentExample example);

    Tb_school_parent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_school_parent record, @Param("example") Tb_school_parentExample example);

    int updateByExample(@Param("record") Tb_school_parent record, @Param("example") Tb_school_parentExample example);

    int updateByPrimaryKeySelective(Tb_school_parent record);

    int updateByPrimaryKey(Tb_school_parent record);
}