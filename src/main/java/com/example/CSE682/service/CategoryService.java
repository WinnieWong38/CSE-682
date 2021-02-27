package com.example.CSE682.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.CategoryRepository;
import com.example.CSE682.model.Category;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	@Override 
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	

}
