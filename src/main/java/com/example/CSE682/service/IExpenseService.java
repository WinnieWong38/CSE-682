package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Expense;

public interface IExpenseService {

	
	List<Expense> getAll();
	
	Expense save(Expense expense);
	
	double getTotalCost();
	
	double getTotalCostByCategory(Long id);
}
