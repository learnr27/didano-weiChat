package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_head_sculpture;
import cn.didano.weichat.model.Tb_head_sculptureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_head_sculptureMapper {
    long countByExample(Tb_head_sculptureExample example);

    int deleteByExample(Tb_head_sculptureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_head_sculpture record);

    int insertSelective(Tb_head_sculpture record);

    List<Tb_head_sculpture> selectByExample(Tb_head_sculptureExample example);

    Tb_head_sculpture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_head_sculpture record, @Param("example") Tb_head_sculptureExample example);

    int updateByExample(@Param("record") Tb_head_sculpture record, @Param("example") Tb_head_sculptureExample example);

    int updateByPrimaryKeySelective(Tb_head_sculpture record);

    int updateByPrimaryKey(Tb_head_sculpture record);
}