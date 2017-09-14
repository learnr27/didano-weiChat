package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_hobby;
import cn.didano.weichat.model.Tb_hobbyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_hobbyMapper {
    long countByExample(Tb_hobbyExample example);

    int deleteByExample(Tb_hobbyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_hobby record);

    int insertSelective(Tb_hobby record);

    List<Tb_hobby> selectByExample(Tb_hobbyExample example);

    Tb_hobby selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_hobby record, @Param("example") Tb_hobbyExample example);

    int updateByExample(@Param("record") Tb_hobby record, @Param("example") Tb_hobbyExample example);

    int updateByPrimaryKeySelective(Tb_hobby record);

    int updateByPrimaryKey(Tb_hobby record);
}