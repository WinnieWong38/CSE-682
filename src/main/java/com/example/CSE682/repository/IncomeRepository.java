package com.example.CSE682.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CSE682.model.Income;

@Repository
public interface IncomeRepository extends CrudRepository<Income, Long>{

	List<Income> findAll();
	
	@Query("select SUM(e.income) from Income e")
	double getTotalIncome();	
	
	//@Delete("se")
/*	@Modifying
	@Query(value = "DELETE FROM Income i where i.description = :description")
	void deleteByValues(@Param("description") String description);
	*/
	
	@Query("select I from Income I where I.description = :description and I.income = :income")
	Long getIdByVal(String description,double income);
	
	void deleteById(Long id);
	
	List<Income> findAll();
	
	
	Optional<Income> findById(Long id);
		
	@Query("select i from Income i where i.incomeid = :id")
	Income getIncomeById(@Param("id") Long id);
	
}
