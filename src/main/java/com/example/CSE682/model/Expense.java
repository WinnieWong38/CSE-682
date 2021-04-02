package com.example.CSE682.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expenseid;

	@Column(name = "expense", length = 50, nullable = false, unique = false)
	private String expense;

	@ManyToOne
	@JoinColumn(name = "category", referencedColumnName = "categoryid")
	private Category category;

	@Column(name = "cost", length = 50, nullable = false, unique = false)
	private double cost;

	@Column(name = "date", nullable = false, unique = false)
	private LocalDate date;

	/**
	 * This method is used to construct a Expense object.
	 */
	@Column(name = "paid", nullable = false, unique = false)
	private boolean isPaid;

	/**
	 * This method is used to construct an Expense object.
	 */
	public Expense() {
	}

	/**
	 * This method is used to construct an Expense object.
	 * 
	 * @param expense  The name of the expense.
	 * @param category The category of the expense.
	 * @param cost     The cost of the expense.
	 * @param date     The date of the expense.
	 * @param isPaid   Whether the expense was paid or not.
	 */
	public Expense(String expense, Category category, double cost, LocalDate date, boolean isPaid) {
		this.expense = expense;
		this.category = category;
		this.cost = cost;
		this.date = date;
		this.isPaid = isPaid;
	}

	/**
	 * This method is used to get the expense ID.
	 * 
	 * @return Long This returns the expense ID.
	 */
	public Long getExpenseid() {
		return expenseid;
	}

	/**
	 * This method is used to set the expense ID.
	 * 
	 * @param expenseid The new ID for the expense.
	 */
	public void setExpenseid(Long expenseid) {
		this.expenseid = expenseid;
	}

	/**
	 * This method is used to get the expense name.
	 * 
	 * @return String This returns the expense name.
	 */
	public String getExpense() {
		return expense;
	}

	/**
	 * This method is used to set the expense name.
	 * 
	 * @param expense The new name for the expense.
	 */
	public void setExpense(String expense) {
		this.expense = expense;
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
	 * This method is used to get the cost.
	 * 
	 * @return double This returns the cost.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * This method is used to set the cost.
	 * 
	 * @param cost The new cost.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * This method is used to get the date.
	 * 
	 * @return LocalDate This returns the date.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * This method is used to set the date.
	 * 
	 * @param date The new date.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * This method is used to get whether the expense is paid.
	 * 
	 * @return boolean This returns whether the expense is paid.
	 */
	public boolean getIsPaid() {
		return isPaid;
	}

	/**
	 * This method is used to set whether the expense is paid.
	 * 
	 * @param isPaid Whether the expense is paid.
	 */
	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

}
