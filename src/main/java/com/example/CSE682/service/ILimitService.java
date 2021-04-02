package com.example.CSE682.service;

import java.util.List;
import java.util.ArrayList;

import com.example.CSE682.model.Limit;

public interface ILimitService {

	/**
	 * This method is used to get all the Limits in the repository.
	 * 
	 * @return List<Limit> This returns the Limits in the repository.
	 */
	List<Limit> getAll();

	/**
	 * This method is used to save an Limit into the repository.
	 * 
	 * @param limit The limit to save.
	 * @return Limit This returns the saved category.
	 */
	Limit save(Limit limit);

	/**
	 * This method is used to edit a limit.
	 * 
	 * @param newLimit The new income to save.
	 * @param id       The ID of the limit to delete.
	 * @return Limit The edited limit.
	 */
	Limit edit(Limit newLimit, Long id);

	/**
	 * This method is used to delete a limit.
	 * 
	 * @param id The ID of the limit to delete.
	 */
	void delete(Long id);

	/**
	 * This method is used to get an Limit for a given id.
	 * 
	 * @param id The id of the Limit.
	 * @return Limit This returns the Limit for a given id.
	 */
	Limit getLimitById(Long id);

	/**
	 * This method is used to populate the limit vs expenses bar chart.
	 * 
	 * @return ArrayList<ArrayList<Object>> This returns a list of lists of
	 * categories, limits, and expenses.
	 */
	ArrayList<ArrayList<Object>> limitBarChar();

	/**
	 * This method is used to get an Income for a given category id.
	 * 
	 * @param id The id of the Category.
	 * @return Income This returns the Income for a given category id.
	 */
	Limit getLimitByIdByCategory(Long id);

	/**
	 * This method is used to set the total Limit for a user.
	 * 
	 * @param Limit The Limit to save.
	 * @return Limit This returns the saved Limit.
	 */
	Limit setTotalLimit(Limit totalLimit);

	/**
	 * This method is used to get the total Limit for a user.
	 * 
	 * @return Limit This returns the total Limit.
	 */
	Limit getTotalLimit();

	/**
	 * This method is used to delete an Income for a given category id.
	 * 
	 * @param id The id of the Category.
	 */
	void deleteByCategory(Long id);

}