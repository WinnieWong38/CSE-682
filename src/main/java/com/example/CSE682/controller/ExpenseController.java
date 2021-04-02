package com.example.CSE682.controller;

import java.util.ArrayList;
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

import com.example.CSE682.model.Expense;
import com.example.CSE682.service.IExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

	@Autowired
	IExpenseService expenseService;

	/**
	 * This method is used to get all expenses.
	 * 
	 * @return List<Expense> This returns all of the expenses from the service.
	 */
	@GetMapping("/getExpenses")
	public List<Expense> getExpenses() {
		return expenseService.getAll();
	}

	/**
	 * This method is used to get an Expense with a specific ID.
	 * 
	 * @param id The ID of the specified Expense.
	 * @return Expense This returns the Expense with the given ID.
	 */
	@GetMapping("/getExpense/{id}")
	public Expense getExpenseById(@PathVariable Long id) {
		return expenseService.getExpenseById(id);
	}

	/**
	 * This method is used to add an Expense.
	 * 
	 * @param expense The Expense to add.
	 * @return Expense The newly added expense.
	 */
	@PostMapping("/addExpense")
	public Expense addExpense(@RequestBody Expense expense) {
		return expenseService.save(expense);
	}

	/**
	 * This method is used to edit an Expense.
	 * 
	 * @param newExpense The new Expense.
	 * @param id         The ID of the Expense to be updated.
	 * @return Expense The updated Expense object.
	 */
	@PutMapping("/editExpense/{id}")
	public Expense editExpense(@RequestBody Expense newExpense, @PathVariable Long id) {
		return expenseService.edit(newExpense, id);
	}

	/**
	 * This method is used to delete an Expense.
	 * 
	 * @param id The ID of the expense to be deleted.
	 */
	@DeleteMapping("/deleteExpense/{id}")
	public void deleteExpense(@PathVariable Long id) {
		expenseService.deleteById(id);
	}

	/**
	 * This method is used to get the total cost for the current user.
	 * 
	 * @return double The sum of expenses for the current user.
	 */
	@GetMapping("/getTotalCost")
	public double getTotalCost() {
		return expenseService.getTotalCost();
	}

	/**
	 * This method is used to get the total cost for the current user for a given
	 * Category.
	 * 
	 * @param id The ID of the Category.
	 * @return double This returns the sum of expenses for the current user for a
	 *         given Category.
	 */
	@GetMapping("/getTotalCostByCategory/{id}")
	public double getTotalCostByCategory(@PathVariable Long id) {
		return expenseService.getTotalCostCurrentMonthByCategoryId(id);
	}

	/**
	 * This method is used to get the total cost for the current user for the
	 * current month.
	 * 
	 * @return double This returns the sum of expenses for the current user for the
	 *         current month.
	 */
	@PostMapping("/getTotalCostCurrentMonth")
	public double getTotalCostCurrentMonth() {
		return expenseService.getTotalCostCurrentMonth();
	}

	/**
	 * This method is used to get a list of Categories and their associated total
	 * costs for the current month.
	 * 
	 * @return ArrayList<ArrayList<Object> This returns a list of Categories and a
	 *         list of expenses (as Doubles).
	 */
	@GetMapping("/getTotalCostCurrentMonthByCategory")
	public ArrayList<ArrayList<Object>> getTotalCostCurrentMonthByCategory() {
		return expenseService.getTotalCostCurrentMonthByCategory();
	}

	/**
	 * This method is used to populate the time series chart.
	 * 
	 * @return ArrayList<ArrayList<Object>> This returns a list of dates (as
	 *         Strings), a list of Categories, and a list of total expenses for each
	 *         Category that month as parallel lists.
	 */
	@GetMapping("/getTimeseriesChart")
	public ArrayList<ArrayList<Object>> getTimeseriesChart() {
		return expenseService.getTimeseriesChart();
	}

	/**
	 * This method is used to populate the monthly summary bar chart.
	 * 
	 * @return ArrayList<Double> This returns 3 doubles: total expenses for the
	 *         current month, monthly income, and total monthly limit.
	 */
	@GetMapping("/getMonthlySummaryBar")
	public ArrayList<Double> getMonthlySummaryBar() {
		return expenseService.getMonthlySummaryBar();
	}
}
