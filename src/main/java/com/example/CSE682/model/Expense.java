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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long expenseid;
    
    @Column(name="expense", length=50, nullable=false, unique=false)
    private String expense;
    
    @ManyToOne
    @JoinColumn(name="category", referencedColumnName="categoryid")
    private Category category;
    
    @Column(name="cost", length=50, nullable=false, unique=false)
    private double cost;
    
    @Column(name="date", nullable=false, unique=false)
    private LocalDate date;
    
    @Column(name="paid", nullable=false, unique=false)
    private boolean isPaid;
    
    public Expense() {}
    
    public Expense(String expense, Category category, double cost, LocalDate date, boolean isPaid) {
    	this.expense = expense;
    	this.category = category;
    	this.cost = cost;
    	this.date = date;
    	this.isPaid = isPaid;
    }

	public Long getExpenseid() {
		return expenseid;
	}

	public void setExpenseid(Long expenseid) {
		this.expenseid = expenseid;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean getIsPaid() {
		return isPaid;
	}
	
	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
}
