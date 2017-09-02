package cn.didano.weichat.model;

public class Hand_parent4mailList{
	private Integer id;
	private Integer relation_id;
	private String parent_name;
	private String parent_phone;
	private String parent_ic_number;
	private Integer icCardId;
	private String rfid;
	private Boolean parent_is_use;
	
	public Boolean getParent_is_use() {
		return parent_is_use;
	}

	public void setParent_is_use(Boolean parent_is_use) {
		this.parent_is_use = parent_is_use;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public Integer getIcCardId() {
		return icCardId;
	}

	public void setIcCardId(Integer icCardId) {
		this.icCardId = icCardId;
	}

	public String getParent_ic_number() {
		return parent_ic_number;
	}

	public void setParent_ic_number(String parent_ic_number) {
		this.parent_ic_number = parent_ic_number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public Integer getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}

	public String getParent_phone() {
		return parent_phone;
	}

	public void setParent_phone(String parent_phone) {
		this.parent_phone = parent_phone;
	}
}
