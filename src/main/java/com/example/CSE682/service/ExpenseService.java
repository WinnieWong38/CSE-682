package com.example.CSE682.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.model.Category;
import com.example.CSE682.model.Expense;
import com.example.CSE682.repository.CategoryRepository;
import com.example.CSE682.repository.ExpenseRepository;

@Service
public class ExpenseService implements IExpenseService{

	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }
	
	@Override 
	public Expense save(Expense expense) {
		return expenseRepository.save(expense);
	}
	
	@Override
	public double getTotalCost() {
		return expenseRepository.getTotalCost();
	}
	
	@Override
	public double getTotalCostByCategory(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return expenseRepository.getTotalCostByCategory(category);
	}
}
