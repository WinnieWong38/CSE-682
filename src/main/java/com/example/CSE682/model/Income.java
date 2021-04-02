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
@Table(name = "income")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long incomeid;

	@Column(name = "income", length = 50, nullable = false, unique = false)
	private String income;

	@Column(name = "cost", length = 50, nullable = false, unique = false)
	private double cost;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private User user;

	/**
	 * This method is used to construct a Income object.
	 */
	public Income() {
	}

	/**
	 * This method is used to construct a Category object.
	 * 
	 * @param income The name of the income.
	 * @param cost   The amount of the income.
	 * @param user   The User who created this category.
	 */
	public Income(String income, double cost, User user) {
		this.income = income;
		this.cost = cost;
		this.user = user;
	}

	/**
	 * This method is used to get the income ID.
	 * 
	 * @return Long This returns income ID.
	 */
	public Long getIncomeid() {
		return incomeid;
	}

	/**
	 * This method is used to set the income ID.
	 * 
	 * @param incomeid The new ID for the income.
	 */
	public void setIncomeid(Long incomeid) {
		this.incomeid = incomeid;
	}

	/**
	 * This method is used to get the income name.
	 * 
	 * @return String This returns the income name.
	 */
	public String getIncome() {
		return income;
	}

	/**
	 * This method is used to set the income name.
	 * 
	 * @param income The new name for the income.
	 */
	public void editIncome(String income) {
		this.income = income;
	}

	/**
	 * This method is used to set the income name.
	 * 
	 * @param income The new name for the income.
	 */
	public void setIncome(String income) {
		this.income = income;
	}

	/**
	 * This method is used to get the amount of the income.
	 * 
	 * @return Long This returns the amount of the income.
	 */
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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
