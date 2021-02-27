package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/getExpenses")
    public List<Expense> getExpenses() {
		return expenseService.getAll();
    }
	
	@PostMapping("/addExpense")
	public Expense addExpense(@RequestBody Expense expense){
		return expenseService.save(expense);
	}
	
	//PutMapping - for edit
	
	//DeleteMapping - for delete
	
	@GetMapping("/getTotalCost")
    public double getTotalCost() {
		return expenseService.getTotalCost();
    }
	
	@GetMapping("/getTotalCostByCategory/{id}")
    public double getTotalCostByCategory(@PathVariable Long id) {
		return expenseService.getTotalCostByCategory(id);
    }
}
