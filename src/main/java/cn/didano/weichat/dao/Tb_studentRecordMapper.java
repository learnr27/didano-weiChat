package cn.didano.weichat.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Tb_studentRecord;
import cn.didano.weichat.model.Tb_studentRecordExample;

public interface Tb_studentRecordMapper {
    long countByExample(Tb_studentRecordExample example);

    int deleteByExample(Tb_studentRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_studentRecord record);

    int insertSelective(Tb_studentRecord record);

    List<Tb_studentRecord> selectByExample(Tb_studentRecordExample example);

    Tb_studentRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_studentRecord record, @Param("example") Tb_studentRecordExample example);

    int updateByExample(@Param("record") Tb_studentRecord record, @Param("example") Tb_studentRecordExample example);

    int updateByPrimaryKeySelective(Tb_studentRecord record);

    int updateByPrimaryKey(Tb_studentRecord record);
}