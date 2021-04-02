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

import com.example.CSE682.model.Income;
import com.example.CSE682.service.IIncomeService;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

	@Autowired
	IIncomeService incomeService;

	/**
	 * This method is used to get an Income with a specific ID.
	 * 
	 * @param id The ID of the specified Income.
	 * @return Income This returns the Income with the given ID.
	 */
	@GetMapping("/getIncome")
	public Income getIncomeById(Long id) {
		return incomeService.getIncomeById(id);
	}

	/**
	 * This method is used to get all incomes.
	 * 
	 * @return List<Income> This returns all of the incomes from the service.
	 */
	@GetMapping("/getIncomes")
	public List<Income> getIncomes() {
		return incomeService.getAllIncomes();
	}

	/**
	 * This method is used to add a Income.
	 * 
	 * @param income The Income to add.
	 * @return Income The newly added income.
	 */
	@PostMapping("/addIncome")
	public Income addIncome(@RequestBody Income income) {
		return incomeService.save(income);
	}

	/**
	 * This method is used to edit an Income.
	 * 
	 * @param newIncome The new Income.
	 * @param id        The ID of the Income to be updated.
	 * @return Income The updated Income object.
	 */
	@PutMapping("/editIncome/{id}")
	public Income editIncome(@RequestBody Income newIncome, @PathVariable Long id) {
		return incomeService.edit(newIncome, id);
	}

	/**
	 * This method is used to delete an Income.
	 * 
	 * @param id The ID of the income to be deleted.
	 * @return void.
	 */
	@DeleteMapping("/deleteIncome/{id}")
	void deleteIncome(@PathVariable Long id) {
		incomeService.delete(id);
	}

	/**
	 * This method is used to get the total income for the current user.
	 * 
	 * @return double The sum of incomes for the current user.
	 */
	@GetMapping("/getTotalIncome")
	public double getTotalIncome() {
		return incomeService.getTotalIncome();
	}
}
