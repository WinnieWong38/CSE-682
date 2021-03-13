package com.example.CSE682.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "userAccount")
public class UserAccount {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userid;
	
	@Column(name="userName", length=50, nullable=false, unique=true)
    private String userName;
	
	@Column(name="password", length=50, nullable=false, unique=false)
    private String password;
	
	public UserAccount() {}
	
	public UserAccount(String UserName, String password) {
		this.userName = UserName;
		this.password = password;
	}

	public Long getUserId() {
		return userid;
	}

	public void setUserId(Long userid) {
		this.userid = userid;
	}

	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String username)
	{
		this.userName = username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
}
