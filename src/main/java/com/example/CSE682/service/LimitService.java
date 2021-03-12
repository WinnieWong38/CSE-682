package com.example.CSE682.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.repository.LimitRepository;
import com.example.CSE682.model.Limit;

@Service
public class LimitService implements ILimitService{
	
	@Autowired
	LimitRepository limitRepository;
	
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
		limit.setLimitId(newLimit.getLimitId());
	    return limitRepository.save(limit);
	}
	
	@Override
	public void delete(Long id) {
		limitRepository.deleteById(id);

	}
}

