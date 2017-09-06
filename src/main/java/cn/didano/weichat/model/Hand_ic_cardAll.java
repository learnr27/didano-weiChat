package cn.didano.weichat.model;

import java.util.List;

/**
 * @author Administrator
 *Rewrite Date：<修改日期:2017-03-09>
 *Log ID：<1>
 *Depiction：<为借口传递数据>
 *Writer：杨朝强
 */
public class Hand_ic_cardAll {
	private Integer staff_id; 
	private List<Hand_ic_type> hand_ic_type;

	public List<Hand_ic_type> getHand_ic_type() {
		return hand_ic_type;
	}

	public void setHand_ic_type(List<Hand_ic_type> hand_ic_type) {
		this.hand_ic_type = hand_ic_type;
	}

	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	@Override
	public String toString() {
		return "Hand_ic_cardAll [staff_id=" + staff_id + ", hand_ic_type=" + hand_ic_type + "]";
	}
	
}
