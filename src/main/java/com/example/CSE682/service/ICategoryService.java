package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Category;
import com.example.CSE682.model.User;

public interface ICategoryService {

	/**
	 * This method is used to get all the Categories in the repository.
	 * 
	 * @return List<Category> This returns the Categories in the repository.
	 */
	List<Category> getAll();

	/**
	 * This method is used to get a Category for a given id.
	 * 
	 * @param id The id of the Category.
	 * @return Category This returns the Category for a given id.
	 */
	Category getCategoryById(Long id);

	/**
	 * This method is used to save a Category into the repository.
	 * 
	 * @param category The category to save.
	 * @return Category This returns the saved category.
	 */
	Category save(Category category);

	/**
	 * This method is used to delete a category.
	 * 
	 * @param id The ID of the category to delete.
	 */
	void deleteById(Long id);

	/**
	 * This method is used to edit a category.
	 * 
	 * @param newCategory The new category to save.
	 * @param id          The ID of the category to delete.
	 * @return Category The edited category.
	 */
	Category edit(Category newCategory, Long id);

	/**
	 * Adds a pre-selected list of default categories for the User.
	 * 
	 * @param user The user who needs default categories.
	 */
	void addDefaultCategories(User user);

}
