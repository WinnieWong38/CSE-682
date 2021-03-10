package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.Limit;

public interface ILimitService {
	
	List<Limit> getAll();
	
	Limit save(Limit limit);
	
	Limit edit(Limit newLimit, Long id);
	
	void delete(Long id);
	
	Limit getLimitById(Long id);

}