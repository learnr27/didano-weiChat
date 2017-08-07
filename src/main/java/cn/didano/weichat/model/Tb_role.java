package cn.didano.weichat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tb_role {
    private Integer id;

    private String name;

    private Date createDate;

    private Date updateDate;

    private Integer deleteFlag;

    private Integer type;

    private Integer no;

    private String description;
    
    private Set<Tb_function> functions = new HashSet<>(0);

    public Set<Tb_function> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<Tb_function> functions) {
		this.functions = functions;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}