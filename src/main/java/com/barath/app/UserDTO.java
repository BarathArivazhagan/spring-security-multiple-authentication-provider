package com.barath.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

@Entity(name = "users")
public class UserDTO {

	private String	userName;
	private String	firstName;
	private String	lastName;
	private String	password;
	private String	roleName = "user";
	private Integer	invalidLoginCount;
	private String	departmentName;
	private Boolean active;
	
	@Transient
	public List<String> getRoles()
	{
		List<String> roleList = new ArrayList<String>();
		roleList.add(roleName);
		return roleList;
	}

	@Column(name = "departmentname")
	public String getDepartmentName()
	{
		return departmentName;
	}
	
	@Id
	@Column(name = "username")
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "firstname")
	public String getFirstName()
	{
		// return firstName == null ? "" :
		// WordUtils.capitalizeFully(firstName);
		return firstName;
	}

	@Column(name = "lastname")
	public String getLastName()
	{
		// return lastName == null ? "" :
		// WordUtils.capitalizeFully(lastName);
		return lastName;
	}

	@Column(name = "password")
	public String getPassword()
	{
		return password;
	}

	@Column(name = "rolename")
	public String getRoleName()
	{
		return roleName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}


	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	@Column(name = "invalidlogincount")
	public Integer getInvalidLoginCount()
	{
		return invalidLoginCount;
	}

	public void setInvalidLoginCount(Integer invalidLoginCount)
	{
		this.invalidLoginCount = invalidLoginCount;
	}

	@Transient
	public String getFullName()
	{
		if (!StringUtils.isEmpty(this.firstName))
			return this.firstName + " " + this.lastName;
		return this.firstName;
	}

	@Column(name = "active")
	public Boolean getActive()
	{
		return active;
	}

	public void setActive(Boolean active)
	{
		this.active = active;
	}

}
