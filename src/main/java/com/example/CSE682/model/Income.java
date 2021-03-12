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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long incomeid;
    
    @Column(name="income", length=50, nullable=false, unique=false)
    private String income;
    
    @ManyToOne
    @JoinColumn(name="category", referencedColumnName="categoryid")
    private Category category;
    
    @Column(name="cost", length=50, nullable=false, unique=false)
    private double cost;
    
    public Income() {}
    
    public Income(String income, Category category, double cost) {
    	this.income = income;
    	this.category = category;
    	this.cost = cost;
    }

	public Long getIncomeid() {
		return incomeid;
	}

	public void setIncomeid(Long incomeid) {
		this.incomeid = incomeid;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
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
