package com.example.CSE682.service;

import java.util.List;
import java.util.ArrayList;

import com.example.CSE682.model.Limit;

public interface ILimitService {
	
	List<Limit> getAll();
	
	Limit save(Limit limit);
	
	Limit edit(Limit newLimit, Long id);
	
	void delete(Long id);
	
	Limit getLimitById(Long id);

	ArrayList<ArrayList> limitBarChar();

	Limit getLimitByIdByCategory(Long id);
	
	Limit setTotalLimit(Limit totalLimit);

	Limit getTotalLimit();

}