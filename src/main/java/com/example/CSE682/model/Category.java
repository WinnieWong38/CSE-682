package com.example.CSE682.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryid;

	@Column(name = "category", length = 50, nullable = false, unique = false)
	private String category;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private User user;

	/**
	 * This method is used to construct a Category object.
	 */
	public Category() {
	}

	/**
	 * This method is used to construct a Category object.
	 * 
	 * @param category The name of the category.
	 */
	public Category(String category) {
		this.category = category;
	}

	/**
	 * This method is used to construct a Category object.
	 * 
	 * @param category The name of the category.
	 * @param user     The User who created this category.
	 */
	public Category(String category, User user) {
		this.category = category;
		this.user = user;
	}

	/**
	 * This method is used to get the category ID.
	 * 
	 * @return Long This returns category ID.
	 */
	public Long getCategoryid() {
		return categoryid;
	}

	/**
	 * This method is used to set the category ID.
	 * 
	 * @param categoryid The new ID for the category.
	 */
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	/**
	 * This method is used to get the category name.
	 * 
	 * @return String This returns the category name.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * This method is used to set the category name.
	 * 
	 * @param category The new name for the category.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * This method is used to get the User who created the category.
	 * 
	 * @return Long This returns category ID.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * This method is used to set the user.
	 * 
	 * @param user The new user for the category.
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
