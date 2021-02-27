package com.example.CSE682.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryid;
	
	@Column(name="category", length=50, nullable=false, unique=false)
    private String category;
	
	public Category() {}
	
	public Category(String category) {
		this.category = category;
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
