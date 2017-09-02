package cn.didano.weichat.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生信息，包括班级、家长、IC卡信息
 * @author stephen.wang  2017年3月31日 
 */
public class Hand_wholeStudentParent4PhoneBook {
	private Integer id;
	private Integer SchoolId;
	private Integer classId;
	private String title;
	private String name;
	private Date birthday;
	private Byte gender;
	private Byte status;
	private Integer relation_id;
	private Integer parent_id;
	private String parent_name;
	private String parent_phone;
	private Boolean deleted;
	private String parent_ic_number;
	private String student_ic_number;
	private Integer parentIcCardId;
	private Integer StudentIcCardId;
	private Integer is_use;
	private Integer switchId;
	private Boolean parent_is_use;
	
	public Boolean getParent_is_use() {
		return parent_is_use;
	}

	public void setParent_is_use(Boolean parent_is_use) {
		this.parent_is_use = parent_is_use;
	}

	public Integer getSwitchId() {
		return switchId;
	}

	public void setSwitchId(Integer switchId) {
		this.switchId = switchId;
	}

	public Integer getIs_use() {
		return is_use;
	}

	public void setIs_use(Integer is_use) {
		this.is_use = is_use;
	}

	public Integer getParentIcCardId() {
		return parentIcCardId;
	}

	public void setParentIcCardId(Integer parentIcCardId) {
		this.parentIcCardId = parentIcCardId;
	}

	public Integer getStudentIcCardId() {
		return StudentIcCardId;
	}

	public void setStudentIcCardId(Integer studentIcCardId) {
		StudentIcCardId = studentIcCardId;
	}

	public String getParent_ic_number() {
		return parent_ic_number;
	}

	public void setParent_ic_number(String parent_ic_number) {
		this.parent_ic_number = parent_ic_number;
	}

	public String getStudent_ic_number() {
		return student_ic_number;
	}

	public void setStudent_ic_number(String student_ic_number) {
		this.student_ic_number = student_ic_number;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getParent_phone() {
		return parent_phone;
	}

	public void setParent_phone(String parent_phone) {
		this.parent_phone = parent_phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return SchoolId;
	}

	public void setSchoolId(Integer schoolId) {
		SchoolId = schoolId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}