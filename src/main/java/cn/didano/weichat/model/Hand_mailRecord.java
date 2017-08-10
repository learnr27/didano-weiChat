package cn.didano.weichat.model;

import java.util.List;

public class Hand_mailRecord {

	private Tb_mail mai;
	private List<Tb_mail_reply> replys;
	public Tb_mail getMai() {
		return mai;
	}
	public void setMai(Tb_mail mai) {
		this.mai = mai;
	}
	public List<Tb_mail_reply> getReplys() {
		return replys;
	}
	public void setReplys(List<Tb_mail_reply> replys) {
		this.replys = replys;
	}
	
}
