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

	/**
	 * This method is used to get a list of all categories.
	 * 
	 * @return List<Category> This returns all of the categories from the service.
	 */
	@GetMapping("/getCategories")
	public List<Category> getCategories() {
		return categoryService.getAll();
	}

	/**
	 * This method is used to get a Category with a specific ID.
	 * 
	 * @param id The ID of the specified Category.
	 * @return Category This returns the Category with the given ID.
	 */
	@GetMapping("/getCategory/{id}")
	public Category getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}

	/**
	 * This method is used to edit a Category.
	 * 
	 * @param newCategory The new Category.
	 * @param id          The ID of the Category to be updated.
	 * @return Category The updated Category object.
	 */
	@PutMapping("/editCategory/{id}")
	public Category editCategory(@RequestBody Category newCategory, @PathVariable Long id) {
		return categoryService.edit(newCategory, id);
	}

	/**
	 * This method is used to delete a Category.
	 * 
	 * @param id The ID of the category to be deleted.
	 * @return void.
	 */
	@DeleteMapping("/deleteCategory/{id}")
	public void deleteCategory(@PathVariable Long id) {
		categoryService.deleteById(id);
	}

	/**
	 * This method is used to add a Category.
	 * 
	 * @param category The Category to add.
	 * @return Category The newly added category.
	 */
	@PostMapping("/addCategory")
	public Category addCategory(@RequestBody Category category) {
		return categoryService.save(category);
	}
}
