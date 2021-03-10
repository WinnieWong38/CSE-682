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
	
	//PutMapping - for edit
	@PutMapping("/editLimit")
	  Limit editLimit(@RequestBody Limit newLimit, @PathVariable Long id) {
	    return limitService.edit(newLimit, id);
	  }
	
	//DeleteMapping - for delete
	 @DeleteMapping("/deleteLimit")
	  void deleteLimit(@PathVariable Long id) {
		 limitService.delete(id);
	  }
	 
	 
	 
	 
}

