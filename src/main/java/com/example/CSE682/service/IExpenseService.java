package com.example.CSE682.service;

import java.util.ArrayList;
import java.util.List;

import com.example.CSE682.model.Expense;

public interface IExpenseService {

	List<Expense> getAll();
	
	Expense getExpenseById(Long id);
	
	Expense save(Expense expense);

	void deleteById(Long id);
	
	double getTotalCost();
	
	double getTotalCostByCategory(Long id);
	
	Expense edit(Expense newExpense, Long id);
	
	public double getTotalCostBetweenTwoDates(String startDate, String endDate);
	
	ArrayList<ArrayList<Object>> getTotalCostCurrentMonthByCategory();
	
	public ArrayList<ArrayList<Object>> getTimeseriesChart();
	
	public double getTotalExpenseToLimitRatio();
	
}
