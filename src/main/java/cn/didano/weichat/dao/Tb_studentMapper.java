package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Hand_parentAndStudentId;
import cn.didano.weichat.model.Tb_student;
import cn.didano.weichat.model.Tb_studentExample;

public interface Tb_studentMapper {
    long countByExample(Tb_studentExample example);

    int deleteByExample(Tb_studentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_student record);

    int insertSelective(Tb_student record);

    List<Tb_student> selectByExample(Tb_studentExample example);

    Tb_student selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_student record, @Param("example") Tb_studentExample example);

    int updateByExample(@Param("record") Tb_student record, @Param("example") Tb_studentExample example);

    int updateByPrimaryKeySelective(Tb_student record);

    int updateByPrimaryKey(Tb_student record);
  //手写
    List<Tb_student> getStudentListByParentPhone(String parentPhone);
    
    Tb_student getStudentByParentId(Hand_parentAndStudentId parentAndStudent);
}