package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.ArrayList;

import com.example.CSE682.model.Limit;
import com.example.CSE682.service.ILimitService;

@RestController
@RequestMapping("/api/limit")
public class LimitController {

	@Autowired
	ILimitService limitService;

	/**
	 * This method is used to get all limits.
	 * 
	 * @return List<Limit> This returns all of the limits from the service.
	 */
	@GetMapping("/getLimit")
	public List<Limit> getLimit() {
		return limitService.getAll();
	}

	/**
	 * This method is used to get a Limit with a specific ID.
	 * 
	 * @param id The ID of the specified Limit.
	 * @return Limit This returns the Limit with the given ID.
	 */
	@GetMapping("/getLimit/{id}")
	public Limit getLimitById(@PathVariable Long id) {
		return limitService.getLimitById(id);
	}

	/**
	 * This method is used to add a Limit.
	 * 
	 * @param limit The Limit to add.
	 * @return Limit The newly added limit.
	 */
	@PostMapping("/addLimit")
	public Limit addLimit(@RequestBody Limit limit) {
		return limitService.save(limit);
	}

	/**
	 * This method is used to edit a Limit.
	 * 
	 * @param newLimit The new Limit.
	 * @param id       The ID of the Limit to be updated.
	 * @return Limit The updated Limit object.
	 */
	@PutMapping("/editLimit/{id}")
	Limit editLimit(@RequestBody Limit newLimit, @PathVariable Long id) {
		return limitService.edit(newLimit, id);
	}

	/**
	 * This method is used to delete a Limit.
	 * 
	 * @param id The ID of the limit to be deleted.
	 * @return void.
	 */
	@DeleteMapping("/deleteLimit/{id}")
	void deleteLimit(@PathVariable Long id) {
		limitService.delete(id);
	}

	/**
	 * This method is used to get a Limit with the given Category.
	 * 
	 * @param categoryId The ID of the Category whose Limit is to be deleted.
	 * @return void.
	 */
	@GetMapping("/getLimitByCategory/{categoryId}")
	public Limit getLimitByCategory(@PathVariable Long categoryId) {
		return limitService.getLimitByIdByCategory(categoryId);
	}

	/**
	 * This method is used to delete a Limit with the given Category.
	 * 
	 * @param categoryId The ID of the Category whose Limit is to be deleted.
	 * @return void.
	 */
	@DeleteMapping("/deleteLimitByCategory/{categoryId}")
	void deleteLimitByCategory(@PathVariable Long categoryId) {
		limitService.deleteByCategory(categoryId);
	}

	/**
	 * This method is used to populate the bar chart for limits, categories, and
	 * expenses.
	 * 
	 * @return ArrayList<ArrayList> Three parallel arrays for expenses, limits, and
	 *         categories.
	 */
	@GetMapping("/getLimitAndCategoriesAndExpenses")
	public ArrayList<ArrayList<Object>> getLimitAndCategoriesAndExpenses() {
		return limitService.limitBarChar();
	}

	/**
	 * This method is used to set a total monthly limit for the current User.
	 * 
	 * @param totalLimit The new total monthly limit for the current User.
	 * @return Limit The updated Limit.
	 */
	@PostMapping("/setTotalLimit")
	public Limit setTotalLimit(@RequestBody Limit totalLimit) {
		return limitService.setTotalLimit(totalLimit);
	}

	/**
	 * This method is used to get a total monthly limit for the current User.
	 * 
	 * @return Limit The updated Limit.
	 */
	@GetMapping("/getTotalLimit")
	public Limit getTotalLimit() {
		return limitService.getTotalLimit();
	}
}
