package com.example.CSE682.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.CategoryRepository;
import com.example.CSE682.model.Category;
import com.example.CSE682.model.User;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	IUserService userService;
	
	@Override
	public List<Category> getAll(){
		List<Category> categories = categoryRepository.findAllByUser(userService.getLoggedinUser());
		
		return categories;
	}
	
	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.getCategoryById(id);
	}
	
	@Override 
	public Category save(Category category) {
		category.setUser(userService.getLoggedinUser());
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
	
	@Override
	public void addDefaultCategories(User user) {
		Category rent = new Category("Rent", user);
		save(rent);
		Category food = new Category("Food", user);
		save(food);
		Category transportation = new Category("Transportation", user);
		save(transportation);
		Category entertainment = new Category("Entertainment", user);
		save(entertainment);
	}
	

}
