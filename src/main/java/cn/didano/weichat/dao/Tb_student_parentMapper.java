package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_student_parent;
import cn.didano.weichat.model.Tb_student_parentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_student_parentMapper {
    long countByExample(Tb_student_parentExample example);

    int deleteByExample(Tb_student_parentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_student_parent record);

    int insertSelective(Tb_student_parent record);

    List<Tb_student_parent> selectByExample(Tb_student_parentExample example);

    Tb_student_parent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_student_parent record, @Param("example") Tb_student_parentExample example);

    int updateByExample(@Param("record") Tb_student_parent record, @Param("example") Tb_student_parentExample example);

    int updateByPrimaryKeySelective(Tb_student_parent record);

    int updateByPrimaryKey(Tb_student_parent record);
}