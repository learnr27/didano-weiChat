package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_staffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_staffMapper {
    long countByExample(Tb_staffExample example);

    int deleteByExample(Tb_staffExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_staff record);

    int insertSelective(Tb_staff record);

    List<Tb_staff> selectByExample(Tb_staffExample example);

    Tb_staff selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_staff record, @Param("example") Tb_staffExample example);

    int updateByExample(@Param("record") Tb_staff record, @Param("example") Tb_staffExample example);

    int updateByPrimaryKeySelective(Tb_staff record);

    int updateByPrimaryKey(Tb_staff record);
}