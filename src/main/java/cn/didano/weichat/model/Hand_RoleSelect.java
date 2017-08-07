package cn.didano.weichat.model;

import java.util.ArrayList;

public class Hand_RoleSelect {
	//角色名称
	private String roleName;
	//角色 数量
	private int num;
	//角色类型
	private int roleType;
	//内容
	private ArrayList<Hand_Role_Weichat> list = new ArrayList();
	
	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<Hand_Role_Weichat> getList() {
		return list;
	}	
}
