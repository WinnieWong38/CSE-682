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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "password", length = 128, nullable = false, unique = false)
	private String password;

	@Column(name = "role", length = 50, nullable = false, unique = false)
	private String role;

	/**
	 * This method is used to construct a User object.
	 */
	public User() {
	}

	/**
	 * This method is used to construct a User object.
	 * 
	 * @param username The username.
	 * @param password The password of the user.
	 * @param role     The role of the user.
	 */
	public User(String username, String password, String role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * This method is used to get the user ID.
	 * 
	 * @return Long This returns the user ID.
	 */
	public Long getid() {
		return id;
	}

	/**
	 * This method is used to set the user ID.
	 * 
	 * @param id The new ID for the user.
	 */
	public void setid(Long id) {
		this.id = id;
	}

	/**
	 * This method is used to get the user name.
	 * 
	 * @return String This returns the user name.
	 */
	public String getUserName() {
		return this.username;
	}

	/**
	 * This method is used to set the user name.
	 * 
	 * @param username The new name for the user.
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * This method is used to get the password.
	 * 
	 * @return String This returns the password.
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * This method is used to set the password.
	 * 
	 * @param password The new password for the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method is used to get the role.
	 * 
	 * @return String This returns the role.
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * This method is used to set the role.
	 * 
	 * @param role The new role for the user.
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
