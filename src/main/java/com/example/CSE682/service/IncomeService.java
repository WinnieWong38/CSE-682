package com.example.CSE682.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.CSE682.model.Income;
import com.example.CSE682.repository.IncomeRepository;

@Service
public class IncomeService implements IIncomeService{

	@Autowired
	IncomeRepository incomeRepository;	
	
	
	@Override
    public List<Income> getAll() {
        return incomeRepository.findAll();
    }
	
	@Override 
	public Income save(Income income) {
		return incomeRepository.save(income);
	}
	
	@Override
	public double getTotalIncome() {
		return incomeRepository.getTotalIncome();
	}
	
	@Override
	public void delete(Long id) {
		incomeRepository.deleteById(id);

	}
	
	@Override
	public Long GetIdByVal(String desc, double income)
	{
		return incomeRepository.getIdByVal(desc, income);	
	}
	
}
