package com.example.CSE682.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.IncomeRepository;
import com.example.CSE682.model.Income;

@Service
public class IncomeService implements IIncomeService {

	@Autowired
	IncomeRepository incomeRepository;

	@Autowired
	IUserService userService;

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#getAllIncomes()
	 */
	public List<Income> getAllIncomes() {
		return incomeRepository.findAllByUser(userService.getLoggedinUser());
	}

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#getIncomeById(Long)
	 */
	public Income getIncomeById(Long id) {
		return incomeRepository.getIncomeById(id);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#getTotalIncome()
	 */
	public double getTotalIncome() {
		return incomeRepository.getTotalIncome(userService.getLoggedinUser());
	}

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#GetIdByVal(String, double)
	 */
	public Long GetIdByVal(String desc, double income) {
		return incomeRepository.getIdByVal(desc, income);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#save(Income)
	 */
	public Income save(Income income) {
		income.setUser(userService.getLoggedinUser());
		return incomeRepository.save(income);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#edit(Income, Long)
	 */
	public Income edit(Income newIncome, Long id) {
		Income income = incomeRepository.getIncomeById(id);
		income.setIncome(newIncome.getIncome());
		income.setCost(newIncome.getCost());
		return incomeRepository.save(income);
	}

	@Override
	/**
	 * @see com.example.CSE682.service.IIncomeService#delete(Long)
	 */
	public void delete(Long id) {
		incomeRepository.deleteById(id);

	}
}