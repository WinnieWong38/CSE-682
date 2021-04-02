package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Income;

public interface IIncomeService {

	/**
	 * This method is used to get the total income for a given user.
	 * 
	 * @return double This returns the sum of incomes.
	 */
	double getTotalIncome();

	/**
	 * This method is used to get ID of an income by value.
	 * 
	 * @param desc   The income description.
	 * @param income The amount of income.
	 * @return Long This returns the ID of an income.
	 */
	Long GetIdByVal(String desc, double income);

	/**
	 * This method is used to save an Income into the repository.
	 * 
	 * @param income The income to save.
	 * @return Income This returns the saved category.
	 */
	Income save(Income income);

	/**
	 * This method is used to edit a Income.
	 * 
	 * @param newIncome The new income to save.
	 * @param id        The ID of the income to delete.
	 * @return Income The edited income.
	 */
	Income edit(Income newIncome, Long id);

	/**
	 * This method is used to delete an income.
	 * 
	 * @param id The ID of the income to delete.
	 */
	void delete(Long id);

	/**
	 * This method is used to get an Income for a given id.
	 * 
	 * @param id The id of the Income.
	 * @return Income This returns the Income for a given id.
	 */
	Income getIncomeById(Long id);

	/**
	 * This method is used to get all the Incomes in the repository.
	 * 
	 * @return List<Income> This returns the Incomes in the repository.
	 */
	List<Income> getAllIncomes();
}