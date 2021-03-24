package com.example.CSE682.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.LimitRepository;
import com.example.CSE682.model.Limit;
import com.example.CSE682.model.Category;

import com.example.CSE682.service.IExpenseService;
import com.example.CSE682.service.ICategoryService;

@Service
public class LimitService implements ILimitService{
	
	@Autowired
	LimitRepository limitRepository;

	@Autowired
	IExpenseService expenseService;

	@Autowired
	ICategoryService categoryService;
	
	@Override
	public List<Limit> getAll(){
		return limitRepository.findAll();
	}
	
	@Override
	public Limit getLimitById(Long id){
		return limitRepository.getLimitById(id);
	}
	
	
	@Override 
	public Limit save(Limit limit) {
		return limitRepository.save(limit);
	}
	
	@Override
	public Limit edit(Limit newLimit, Long id) {
		Limit limit = limitRepository.getLimitById(id);
		limit.setLimit(newLimit.getLimit());
		limit.setCategory(newLimit.getCategory());
//		limit.setLimitId(newLimit.getLimitId());
	    return limitRepository.save(limit);
	}
	
	@Override
	public void delete(Long id) {
		limitRepository.deleteById(id);

	}

	@Override
	public ArrayList<ArrayList> limitBarChar() {
		ArrayList<ArrayList> returnList = new ArrayList<>();
		ArrayList<String> categories = new ArrayList<>();
		ArrayList<Double> expenses = new ArrayList<>();
		ArrayList<Double> limits = new ArrayList<>();
		for(Limit limit : getAll()){
			categories.add(limit.getCategory().getCategory());
			limits.add(limit.getLimit());
			expenses.add(expenseService.getTotalCostByCategory(limit.getCategory().getCategoryid()));
		}
		
		returnList.add(limits);
		returnList.add(expenses);
		returnList.add(categories);
		return returnList;
	}

	@Override
	public Limit getLimitByIdByCategory(Long id) {
		Category category = categoryService.getCategoryById(id);
		return limitRepository.getLimitByIdByCategory(category);
	}
	
	@Override
	public Limit setTotalLimit(Limit totalLimit) {
		Limit total_limit_repository = limitRepository.getTotalLimit();
		if (total_limit_repository == null) {
			total_limit_repository = limitRepository.save(totalLimit);
		} else {
			total_limit_repository.setLimit(totalLimit.getLimit());
			total_limit_repository = limitRepository.save(total_limit_repository);
		}
		
		return total_limit_repository;
	}

	@Override
	public Limit getTotalLimit(){
		return limitRepository.getTotalLimit();
	}
}

