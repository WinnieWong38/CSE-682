package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.ArrayList;


import com.example.CSE682.model.Limit;
import com.example.CSE682.service.ILimitService;

@RestController
@RequestMapping("/api/limit")
public class LimitController {

	@Autowired
	ILimitService limitService;
	
	@GetMapping("/getLimit")
    public List<Limit> getLimit() {
		return limitService.getAll();
    }
	
	@GetMapping("/getLimit/{id}")
    public Limit getLimitById(@PathVariable Long id) {
		return limitService.getLimitById(id);
    }
	
	@PostMapping("/addLimit")
	public Limit addLimit(@RequestBody Limit limit){
		return limitService.save(limit);
	}
	
	@PutMapping("/editLimit/{id}")
	Limit editLimit(@RequestBody Limit newLimit, @PathVariable Long id) {
		return limitService.edit(newLimit, id);
	}
	
	@DeleteMapping("/deleteLimit/{id}")
	void deleteLimit(@PathVariable Long id) {
		limitService.delete(id);
	}
	 
	@GetMapping("/getLimitAndCategoriesAndExpenses")
	public ArrayList<ArrayList> getLimitAndCategoriesAndExpenses() {
		return limitService.limitBarChar();
	}
	
	@GetMapping("/getLimitByCategory/{id}")
	public Limit getLimitByCategory(@PathVariable Long id) {
		return limitService.getLimitByIdByCategory(id);
	}
	 
	@PostMapping("/setTotalLimit")
	public Limit setTotalLimit(@RequestBody Limit totalLimit) {
		return limitService.setTotalLimit(totalLimit);
	}

	@GetMapping("/getTotalLimit")
    public Limit getTotalLimit() {
		return limitService.getTotalLimit();
    }
}

