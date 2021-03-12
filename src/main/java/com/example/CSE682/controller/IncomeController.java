package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CSE682.model.Income;
import com.example.CSE682.service.IIncomeService;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

	@Autowired
	IIncomeService incomeService;
	
	@GetMapping("/getIncomes")
    public List<Income> getIncomes() {
		return incomeService.getAll();
    }
	
	@PostMapping("/addIncome")
	public Income addIncome(@RequestBody Income income){
		return incomeService.save(income);
	}
	
	//PutMapping - for edit
	
	//DeleteMapping - for delete
	@DeleteMapping("/deleteVal/{id}")
	  void deleteLimit(@PathVariable Long id) {
		incomeService.delete(id);
	  }
	
	
	@GetMapping("/getTotalIncome")
    public double getTotalIncome() {
		return incomeService.getTotalIncome();
    }
	
	@GetMapping("/getIdByVal")
	public Long GetIdByVal(@PathVariable String desc, @PathVariable double income)
	{
		return incomeService.GetIdByVal(desc, income);
	}
}
