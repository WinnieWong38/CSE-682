package com.example.CSE682.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.CategoryRepository;
import com.example.CSE682.model.Category;
import com.example.CSE682.model.Expense;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
	
	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.getCategoryById(id);
	}
	
	@Override 
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	@Override
	public Category edit(Category newCategory, Long id) {
		Category category = categoryRepository.getCategoryById(id);
		category.setCategory(newCategory.getCategory());
		return categoryRepository.save(category);
	}
	
	@Override 
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}
	

}
