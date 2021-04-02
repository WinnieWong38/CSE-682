package com.example.CSE682.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "CategoryLimit")
public class Limit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long limitid;

	@Column(name = "CategoryLimit", length = 50, nullable = false, unique = false)
	private Double limit;

	@ManyToOne
	@JoinColumn(name = "category", referencedColumnName = "categoryid")
	private Category category;

	@Column(name = "isTotal", nullable = false, unique = false)
	private boolean isTotal;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private User user;

	/**
	 * This method is used to construct a Limit object.
	 */
	public Limit() {
	}

	/**
	 * This method is used to construct a Expense object.
	 * 
	 * @param limit    The limit amount.
	 * @param category The category of the expense.
	 * @param isTotal  Whether the Limit is a total limit ie not category-specific.
	 */
	public Limit(Double limit, Category category, boolean isTotal) {
		this.limit = limit;
		this.category = category;
		this.isTotal = isTotal;
	}

	/**
	 * This method is used to construct a Expense object.
	 * 
	 * @param limit    The limit amount.
	 * @param category The category of the expense.
	 * @param isTotal  Whether the Limit is a total limit ie not category-specific.
	 * @param user     The user that made the Limit.
	 */
	public Limit(Double limit, Category category, boolean isTotal, User user) {
		this.limit = limit;
		this.category = category;
		this.isTotal = isTotal;
		this.user = user;
	}

	/**
	 * This method is used to get the limit ID.
	 * 
	 * @return Long This returns the limit ID.
	 */
	public Long getLimitId() {
		return limitid;
	}

	/**
	 * This method is used to set the limit ID.
	 * 
	 * @param limitid The new ID for the limit.
	 */
	public void setLimitId(Long limitid) {
		this.limitid = limitid;
	}

	/**
	 * This method is used to get the limit.
	 * 
	 * @return double This returns the limit.
	 */
	public double getLimit() {
		return limit;
	}

	/**
	 * This method is used to set the limit.
	 * 
	 * @param cost The new limit.
	 */
	public void setLimit(Double limit) {
		this.limit = limit;
	}

	/**
	 * This method is used to get the category.
	 * 
	 * @return Category This returns the category.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * This method is used to set the category.
	 * 
	 * @param category The new category.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * This method is used to get whether the limit is a total limit.
	 * 
	 * @return boolean This returns whether the limit is a total limit.
	 */
	public boolean getIsTotal() {
		return isTotal;
	}

	/**
	 * This method is used to set whether the limit is a total limit.
	 * 
	 * @param isTotal Whether the limit is a total limit.
	 */
	public void setIsTotal(boolean isTotal) {
		this.isTotal = isTotal;
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
