package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Tb_benchmark;


public interface Hand_benchmarkMapper {
	List<Tb_benchmark> selectByPrimaryKey(Tb_benchmark tb);
}