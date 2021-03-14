package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Income;

public interface IIncomeService {
	

	Income save(Income income);
	
	Income edit(Income newIncome, Long id);
	
	void delete(Long id);
	
	Income getIncomeById(Long id);
	
	List<Income> getAllIncomes();


}

/*package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Income;

public interface IIncomeService {
	
	List<Income> getAll();
	
	Income save(Income income);

	Long GetIdByVal(String desc, double income);
	
	double getTotalIncome();
	
	void delete(Long id);
	
} */
