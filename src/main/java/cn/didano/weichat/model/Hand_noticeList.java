package cn.didano.weichat.model;

import java.util.ArrayList;
import java.util.List;

public class Hand_noticeList {

	private List<Hand_homeMailBox> mailBox ;
	private List<Tb_notice> notices ;
	
	public List<Hand_homeMailBox> getMailBox() {
		return mailBox;
	}
	public void setMailBox(List<Hand_homeMailBox> mailBox) {
		this.mailBox = mailBox;
	}
	public List<Tb_notice> getNotices() {
		return notices;
	}
	public void setNotices(List<Tb_notice> notices) {
		this.notices = notices;
	}
	
}
