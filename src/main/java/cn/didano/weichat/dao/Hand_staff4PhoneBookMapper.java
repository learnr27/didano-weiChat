package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Hand_staff4PhoneBook;
import cn.didano.weichat.model.Tb_staff;
import cn.didano.weichat.model.Tb_staffExample;

public interface Hand_staff4PhoneBookMapper {
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
    
    List<Hand_staff4PhoneBook> selectSchoolAllStaff(Integer schoolId); 
    List<Hand_staff4PhoneBook> selectSchoolAllAdministrationInteger(Integer schoolId); 
}