package cn.didano.weichat.json;


import io.swagger.annotations.ApiModelProperty;

public class In_Parent_Edit {
	@ApiModelProperty(value = "家长id",required=true)
	private Integer Id;
	@ApiModelProperty(value = "当前学生id",required=true)
	private Integer studentId;
	@ApiModelProperty(value = "学校id",required=true)
	private Integer schoolId;
	@ApiModelProperty(value = "名字d",required=true)
	private String name;
	@ApiModelProperty(value = "老师电话",required=true)
	private String phone ;
	@ApiModelProperty(value = "所带班级",required=true)
	private Integer classId;
	
	
	@ApiModelProperty(value = "关系id",required=true)
	private byte relationId;
	
	@ApiModelProperty(value = "ic卡的id编号",required=true)
	private Integer icCardId;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public byte getRelationId() {
		return relationId;
	}

	public void setRelationId(byte relationId) {
		this.relationId = relationId;
	}

	public Integer getIcCardId() {
		return icCardId;
	}

	public void setIcCardId(Integer icCardId) {
		this.icCardId = icCardId;
	}
	
	
	
	
}
