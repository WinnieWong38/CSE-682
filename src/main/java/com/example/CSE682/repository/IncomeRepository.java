package com.example.CSE682.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CSE682.model.Income;
import com.example.CSE682.model.User;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long>{

	List<Income> findAll();
	
	@Query("select SUM(i.cost) from Income i where i.user = :user")
	double getTotalIncome(@Param("user") User user);
	
	@Query("select I from Income I where I.income = :income and I.cost = :cost")
	Long getIdByVal(String income, double cost);
	
	void deleteById(Long id);
	
	Optional<Income> findById(Long id);
	
	@Query("select i from Income i where i.incomeid = :id")
	Income getIncomeById(@Param("id") Long id);
	
}
