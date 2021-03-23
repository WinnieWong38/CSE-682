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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long limitid;
	
	@Column(name="CategoryLimit", length=50, nullable=false, unique=false)
    private Double limit;
	
	@ManyToOne
    @JoinColumn(name="category", referencedColumnName="categoryid")
    private Category category;
	
	@Column(name="isTotal", nullable=false, unique=false)
	private boolean isTotal;
	
	public Limit() {}
	
	public Limit(Double limit, Category category, boolean isTotal) {
		this.limit = limit;
		this.category = category;
		this.isTotal = isTotal;
	}

	public Long getLimitId() {
		return limitid;
	}

	public void setLimitId(Long limitid) {
		this.limitid = limitid;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public boolean getIsTotal() {
		return isTotal;
	}
	
	public void setIsTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}
}
