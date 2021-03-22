package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/getCategory/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
	
	@PutMapping("/editCategory/{id}")
	public Category editExpense(@RequestBody Category newCategory, @PathVariable Long id) {
	    return categoryService.edit(newCategory, id);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public void deleteExpense(@PathVariable Long id){
		categoryService.deleteById(id);
	}
	
	@PostMapping("/addCategory")
	public Category addExpense(@RequestBody Category category){
		return categoryService.save(category);
	}
}
