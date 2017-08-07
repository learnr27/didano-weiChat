package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

public class Hand_homeMailBox {

	private String name;
	private List<Tb_notice> mailBox = new ArrayList<Tb_notice>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Tb_notice> getMailBox() {
		return mailBox;
	}
	public void setMailBox(List<Tb_notice> mailBox) {
		this.mailBox = mailBox;
	}
    
}
