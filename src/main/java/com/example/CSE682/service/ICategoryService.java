package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Category;

public interface ICategoryService {
	
	List<Category> getAll();
	
	Category save(Category category);

}
