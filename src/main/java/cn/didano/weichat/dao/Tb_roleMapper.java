package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_role;
import cn.didano.weichat.model.Tb_roleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_roleMapper {
    long countByExample(Tb_roleExample example);

    int deleteByExample(Tb_roleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_role record);

    int insertSelective(Tb_role record);

    List<Tb_role> selectByExample(Tb_roleExample example);

    Tb_role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_role record, @Param("example") Tb_roleExample example);

    int updateByExample(@Param("record") Tb_role record, @Param("example") Tb_roleExample example);

    int updateByPrimaryKeySelective(Tb_role record);

    int updateByPrimaryKey(Tb_role record);
}