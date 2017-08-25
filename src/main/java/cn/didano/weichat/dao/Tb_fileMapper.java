package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_file;
import cn.didano.weichat.model.Tb_fileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_fileMapper {
    long countByExample(Tb_fileExample example);

    int deleteByExample(Tb_fileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_file record);

    int insertSelective(Tb_file record);

    List<Tb_file> selectByExample(Tb_fileExample example);

    Tb_file selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_file record, @Param("example") Tb_fileExample example);

    int updateByExample(@Param("record") Tb_file record, @Param("example") Tb_fileExample example);

    int updateByPrimaryKeySelective(Tb_file record);

    int updateByPrimaryKey(Tb_file record);
}