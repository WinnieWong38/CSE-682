package com.example.CSE682.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "useraccount")
public class User {
	

    

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(name="username", length=50, nullable=false, unique=true)
    private String username;
	
	@Column(name="password", length=128, nullable=false, unique=false)
    private String password;
	
	@Column(name="role", length=50, nullable=false, unique=false)
	private String role;
	
	
	/*@Column(name="enabled", nullable=false, unique=false)
	private boolean enabled;
	*/
	
	public User() {}
	
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public String getUserName()
	{
		return this.username;
	}
	
	public void setUserName(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getRole()
	{
		return this.role;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}
	
	/*public boolean getEnabled()
	{
		return this.enabled;
	}
	
	public void setRole(boolean enabled)
	{
		this.enabled = enabled;
	}
	*/
}
