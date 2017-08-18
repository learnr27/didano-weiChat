package cn.didano.weichat.dao;

import java.util.List;

import cn.didano.weichat.model.Hand_icCardAndSchool_id;
import cn.didano.weichat.model.Hand_ic_card;
import cn.didano.weichat.model.Tb_ic_card;

public interface Hand_ic_cardMapper {
	List<Tb_ic_card> selectNoeIcInfo(Tb_ic_card ic);
	
	List<Tb_ic_card> select_between_icInfo(Hand_icCardAndSchool_id hciCard);
	
	List<Tb_ic_card> select_ICMaxNumber(int school_id);
	
	int updateInfoByid(int id);
	
	int updateInfoByic_number(Tb_ic_card td);
	
	int updateIcCardInfo(Hand_ic_card h);
	
	int updateChongZhiIcCardInfo(Tb_ic_card h);
	
	int updateChongZhiStudentInfo(Tb_ic_card h);
	
	int updateChongZhiParentInfo(Tb_ic_card h);
	
	int updateChongZhiTeacherInfo(Tb_ic_card h);
	
	int remove_icCard(Tb_ic_card ic_card);
	
	int remove_rfidAndIcNumber(Tb_ic_card ic_card);
}