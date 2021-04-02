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

	/**
	 * This method is used to get all the Incomes in the repository.
	 * 
	 * @return List<Income> This returns the Incomes in the repository.
	 */
	List<Income> findAll();
	
	/**
	 * This method is used to get all the Incomes for a given user.
	 * 
	 * @param user The user whose incomes should be returned.
	 * @return List<Income> This returns the Incomes for a given user.
	 */
	@Query("select i from Income i where i.user = :user")
	List<Income> findAllByUser(@Param("user") User user);

	/**
	 * This method is used to get the number of incomes for a given user.
	 * 
	 * @param user The user whose incomes should be counted.
	 * @return int This returns the number of incomes for the user.
	 */
	@Query("select COUNT(i) from Income i where i.user = :user")
	int getCountTotalIncome(@Param("user") User user);

	/**
	 * This method is used to get the total income for a given user.
	 * 
	 * @param user The user to filter the repository by.
	 * @return double This returns the sum of incomes.
	 */
	@Query("select SUM(i.cost) from Income i where i.user = :user")
	double getTotalIncome(@Param("user") User user);

	/**
	 * This method is used to get the ID for a given Income.
	 * 
	 * @param income The name of the income.
	 * @param cost The amount of the income.
	 * @return Long This returns the ID of the income.
	 */
	@Query("select I from Income I where I.income = :income and I.cost = :cost")
	Long getIdByVal(String income, double cost);

	/**
	 * This method is used to remove an Income.
	 * 
	 * @param id The ID of the income.
	 */
	void deleteById(Long id);
	
	/**
	 * This method is used to get an Income for a given id.
	 * 
	 * @param id The id of the Income.
	 * @return Optional<Income> This returns the Income for a given id.
	 */
	Optional<Income> findById(Long id);
	
	/**
	 * This method is used to get a Income for a given id.
	 * 
	 * @param id The id of the Income.
	 * @return Income This returns the Income for a given id.
	 */
	@Query("select i from Income i where i.incomeid = :id")
	Income getIncomeById(@Param("id") Long id);
	
}
