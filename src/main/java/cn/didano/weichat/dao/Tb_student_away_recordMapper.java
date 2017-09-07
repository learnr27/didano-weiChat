package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Hand_studentAwayData;
import cn.didano.weichat.model.Tb_student_away_record;
import cn.didano.weichat.model.Tb_student_away_recordExample;

public interface Tb_student_away_recordMapper {
    long countByExample(Tb_student_away_recordExample example);

    int deleteByExample(Tb_student_away_recordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_student_away_record record);

    int insertSelective(Tb_student_away_record record);

    List<Tb_student_away_record> selectByExample(Tb_student_away_recordExample example);

    Tb_student_away_record selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_student_away_record record, @Param("example") Tb_student_away_recordExample example);

    int updateByExample(@Param("record") Tb_student_away_record record, @Param("example") Tb_student_away_recordExample example);

    int updateByPrimaryKeySelective(Tb_student_away_record record);

    int updateByPrimaryKey(Tb_student_away_record record);
    //手写
    List<Tb_student_away_record> selectStudentAwayRecordById(Hand_studentAwayData data);
}