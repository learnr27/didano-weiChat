package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_function;
import cn.didano.weichat.model.Tb_functionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_functionMapper {
    long countByExample(Tb_functionExample example);

    int deleteByExample(Tb_functionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_function record);

    int insertSelective(Tb_function record);

    List<Tb_function> selectByExample(Tb_functionExample example);

    Tb_function selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_function record, @Param("example") Tb_functionExample example);

    int updateByExample(@Param("record") Tb_function record, @Param("example") Tb_functionExample example);

    int updateByPrimaryKeySelective(Tb_function record);

    int updateByPrimaryKey(Tb_function record);
}