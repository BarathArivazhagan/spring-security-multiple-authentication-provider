package com.barath.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "roles")
public class RoleDTO
{

	private String	roleId;

	private String	roleName;

	public RoleDTO()
	{
		super();
	}

	public RoleDTO(String roleName)
	{
		super();
		this.roleName = roleName;
	}

	@Id
	@Column(name = "roleid")
	public String getRoleId()
	{
		return roleId;
	}

	@Column(name = "rolename")
	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

}