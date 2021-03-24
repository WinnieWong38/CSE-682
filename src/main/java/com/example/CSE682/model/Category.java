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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryid;
	
	@Column(name="category", length=50, nullable=false, unique=false)
    private String category;
	
	@ManyToOne
	@JoinColumn(name="userid", referencedColumnName="id")
	private User user;
	
	public Category() {}
	
	public Category(String category) {
		this.category = category;
	}
	
	public Category(String category, User user) {
		this.category = category;
		this.user = user;
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
