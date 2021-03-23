package com.example.CSE682.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CSE682.model.Category;
import com.example.CSE682.model.Expense;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>{

	List<Expense> findAll();
	
	@Query("select e from Expense e where e.expenseid = :id")
	Expense getExpenseById(@Param("id") Long id);
	
	@Query("select SUM(e.cost) from Expense e")
	double getTotalCost();
	
	@Query("select SUM(e.cost) from Expense e where e.category = :category")
	Double getTotalCostByCategory(@Param("category") Category category);
	
	@Query("select COUNT(e) from Expense e where e.category = :category")
	int getCountByCategory(@Param("category") Category category);
	
	@Query("select SUM(e.cost) from Expense e where :startDate <= e.date AND :endDate >= e.date")
	double getTotalCostBetweenTwoDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query("SELECT MIN(date) FROM Expense")
	LocalDate getEarliestDate();
	
	@Query("select SUM(e.cost) from Expense e where :startDate <= e.date AND :endDate >= e.date AND e.category = :category")
	double getTotalCostBetweenTwoDatesByCategory(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Category category);

	Expense saveAndFlush(Expense expense);
	
}
