package com.example.CSE682.service;

import java.util.ArrayList;
import java.util.List;

import com.example.CSE682.model.Expense;

public interface IExpenseService {

	/**
	 * This method is used to get all the Expenses in the repository.
	 * 
	 * @return List<Expense> This returns the Expenses in the repository.
	 */
	List<Expense> getAll();

	/**
	 * This method is used to get a Expense for a given id.
	 * 
	 * @param id The id of the Expense.
	 * @return Expense This returns the Expense for a given id.
	 */
	Expense getExpenseById(Long id);

	/**
	 * This method is used to save an expense into the repository.
	 * 
	 * @param expense The expense to save.
	 * @return Expense This returns the saved expense.
	 */
	Expense save(Expense expense);

	/**
	 * This method is used to delete an expense.
	 * 
	 * @param id The expense to save.
	 */
	void deleteById(Long id);

	/**
	 * This method is used to get the total cost for a given user.
	 * 
	 * @return double This returns the sum of expenses.
	 */
	double getTotalCost();

	/**
	 * This method is used to get the total cost for a given category for a given
	 * user.
	 * 
	 * @param id The category id to filter the repository by.
	 * @return double This returns the sum of expenses for the Category.
	 */
	double getTotalCostCurrentMonthByCategoryId(Long id);

	Expense edit(Expense newExpense, Long id);

	public double getTotalCostCurrentMonth();

	ArrayList<ArrayList<Object>> getTotalCostCurrentMonthByCategory();

	public ArrayList<ArrayList<Object>> getTimeseriesChart();

	public ArrayList<Double> getMonthlySummaryBar();

}
