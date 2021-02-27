package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CSE682.service.ICategoryService;
import com.example.CSE682.model.Category;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/getCategories")
	public List<Category> getCategories(){
		return categoryService.getAll();
	}
	
	@PostMapping("/addCategory")
	public Category addExpense(@RequestBody Category category){
		return categoryService.save(category);
	}
	
	
}
