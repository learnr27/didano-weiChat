package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Tb_student_detection;


public interface Tb_student_selectMapper {
	List<Tb_student_detection> selectByPrimaryKey_student(Integer id);
	
}