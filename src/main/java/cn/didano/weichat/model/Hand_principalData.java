package cn.didano.weichat.model;

import java.util.List;

public class Hand_principalData {
	private List<Tb_principal_notice> principalNotices;
	private List<String> readerNames;
	public List<Tb_principal_notice> getPrincipalNotices() {
		return principalNotices;
	}
	public void setPrincipalNotices(List<Tb_principal_notice> principalNotices) {
		this.principalNotices = principalNotices;
	}
	public List<String> getReaderNames() {
		return readerNames;
	}
	public void setReaderNames(List<String> readerNames) {
		this.readerNames = readerNames;
	}
	

}
