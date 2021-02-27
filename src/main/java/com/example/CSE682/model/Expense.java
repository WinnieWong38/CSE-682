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
    
    public Expense() {}
    
    public Expense(String expense, Category category, double cost) {
    	this.expense = expense;
    	this.category = category;
    	this.cost = cost;
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
	
	
}
