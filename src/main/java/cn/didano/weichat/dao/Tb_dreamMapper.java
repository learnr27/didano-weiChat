package cn.didano.weichat.dao;

import cn.didano.weichat.model.Tb_dream;
import cn.didano.weichat.model.Tb_dreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Tb_dreamMapper {
    long countByExample(Tb_dreamExample example);

    int deleteByExample(Tb_dreamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_dream record);

    int insertSelective(Tb_dream record);

    List<Tb_dream> selectByExample(Tb_dreamExample example);

    Tb_dream selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_dream record, @Param("example") Tb_dreamExample example);

    int updateByExample(@Param("record") Tb_dream record, @Param("example") Tb_dreamExample example);

    int updateByPrimaryKeySelective(Tb_dream record);

    int updateByPrimaryKey(Tb_dream record);
}