package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Tb_schoolParent;
import cn.didano.weichat.model.Tb_schoolParentExample;
import cn.didano.weichat.model.Tb_schoolparent4;

public interface Tb_schoolparentMapper {
    long countByExample(Tb_schoolParentExample example);

    int deleteByExample(Tb_schoolParentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_schoolParent record);

    int insertSelective(Tb_schoolParent record);

    List<Tb_schoolParent> selectByExample(Tb_schoolParentExample example);

    Tb_schoolParent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_schoolParent record, @Param("example") Tb_schoolParentExample example);

    int updateByExample(@Param("record") Tb_schoolParent record, @Param("example") Tb_schoolParentExample example);

    int updateByPrimaryKeySelective(Tb_schoolParent record);

    int updateByPrimaryKey(Tb_schoolParent record);

	int insertSelective(Tb_schoolparent4 record);
}