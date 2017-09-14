package cn.didano.weichat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.didano.weichat.model.Hand_noticeUserData;
import cn.didano.weichat.model.Tb_noticeUser;
import cn.didano.weichat.model.Tb_noticeUserExample;

public interface Tb_noticeUserMapper {
    long countByExample(Tb_noticeUserExample example);

    int deleteByExample(Tb_noticeUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Tb_noticeUser record);

    int insertSelective(Tb_noticeUser record);

    List<Tb_noticeUser> selectByExample(Tb_noticeUserExample example);

    Tb_noticeUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Tb_noticeUser record, @Param("example") Tb_noticeUserExample example);

    int updateByExample(@Param("record") Tb_noticeUser record, @Param("example") Tb_noticeUserExample example);

    int updateByPrimaryKeySelective(Tb_noticeUser record);

    int updateByPrimaryKey(Tb_noticeUser record);
    //手写
    List<Tb_noticeUser> selectByNoticeType(Hand_noticeUserData data);
}