package com.example.CSE682.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.IncomeRepository;
import com.example.CSE682.model.Income;

@Service
public class IncomeService implements IIncomeService{
	
	@Autowired
	IncomeRepository incomeRepository;
	
	@Autowired
	IUserService userService;
	
	@Override
	public List<Income> getAllIncomes() {
		return incomeRepository.findAllByUser(userService.getLoggedinUser());
	}
	
	@Override
	public Income getIncomeById(Long id) {
		return incomeRepository.getIncomeById(id);
	}
	
	@Override
	public double getTotalIncome() {
		return incomeRepository.getTotalIncome(userService.getLoggedinUser());
	}
	
	@Override
	public Long GetIdByVal(String desc, double income) {
		return incomeRepository.getIdByVal(desc, income);	
	}
	
	@Override 
	public Income save(Income income) {
		income.setUser(userService.getLoggedinUser());
		return incomeRepository.save(income);
	}
	
	@Override
	public Income edit(Income newIncome, Long id) {
		Income income = incomeRepository.getIncomeById(id);
		income.setIncome(newIncome.getIncome());
		income.setCost(newIncome.getCost());
	    return incomeRepository.save(income);
	}
	
	@Override
	public void delete(Long id) {
		incomeRepository.deleteById(id);

	}
}