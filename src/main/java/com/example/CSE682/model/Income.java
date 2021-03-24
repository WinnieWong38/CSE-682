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
    
    @Column(name="cost", length=50, nullable=false, unique=false)
    private double cost;
	
	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName="id")
	private User user;
    
    public Income() {}
    
    public Income(String income, double cost, User user) {
    	this.income = income;
    	this.cost = cost;
    	this.user = user;
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
	public void editIncome(String income) {
		this.income = income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	
}
