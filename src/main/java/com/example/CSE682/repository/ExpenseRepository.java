package com.example.CSE682.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CSE682.model.Category;
import com.example.CSE682.model.Expense;
import com.example.CSE682.model.User;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>{

	/**
	 * This method is used to get all the Expenses in the repository.
	 * 
	 * @return List<Expense> This returns the Expenses in the repository.
	 */
	List<Expense> findAll();
	
	/**
	 * This method is used to get all the Expenses for a given user.
	 * 
	 * @param user The user whose expenses should be returned.
	 * @return List<Expense> This returns the Expenses for a given user.
	 */
	@Query("select e from Expense e where e.category.user = :user")
	List<Expense> findAllByUser(@Param("user") User user);

	/**
	 * This method is used to get a Expense for a given id.
	 * 
	 * @param id The id of the Expense.
	 * @return Expense This returns the Expense for a given id.
	 */
	@Query("select e from Expense e where e.expenseid = :id")
	Expense getExpenseById(@Param("id") Long id);

	/**
	 * This method is used to get the total cost for a given user.
	 * 
	 * @param user The user to filter the repository by.
	 * @return double This returns the sum of expenses.
	 */
	@Query("select SUM(e.cost) from Expense e where e.category.user = :user")
	double getTotalCost(@Param("user") User user);

	/**
	 * This method is used to get the total cost for a given category for a given user.
	 * 
	 * @param category The category to filter the repository by.
	 * @param user The user to filter the repository by.
	 * @return Double This returns the sum of expenses for the Category.
	 */
	@Query("select SUM(e.cost) from Expense e where e.category = :category")
	Double getTotalCostByCategory(@Param("category") Category category);

	/**
	 * This method is used to get the number of expenses for a given category.
	 * 
	 * @param category The category to filter the repository by.
	 * @return int This returns the number of expenses for the Category.
	 */
	@Query("select COUNT(e) from Expense e where e.category = :category")
	int getCountByCategory(@Param("category") Category category);

	/**
	 * This method is used to get the total cost within a range of dates for a given user.
	 * 
	 * @param startDate The first date of the range.
	 * @param endDate The last date of the range.
	 * @param user The user to filter the repository by.
	 * @return Double This returns the sum of expenses within a range of dates for a given user.
	 */
	@Query("select COUNT(e) from Expense e where :startDate <= e.date AND :endDate >= e.date AND e.category.user = :user")
	double getCountBetweenTwoDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("user") User user);

	/**
	 * This method is used to get the number of expenses within a range of dates for a given category.
	 * 
	 * @param category The category to filter the repository by.
	 * @param startDate The first date of the range.
	 * @param endDate The last date of the range.
	 * @return double This returns the sum of expenses within a range of dates for a given category.
	 */
	@Query("select COUNT(e) from Expense e where e.category = :category AND :startDate <= e.date AND :endDate >= e.date")
	double getCountByCategoryBetweenTwoDates(@Param("category") Category category, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

	/**
	 * This method is used to get the total of expenses within a range of dates for a given User.
	 * 
	 * @param startDate The first date of the range.
	 * @param endDate The last date of the range.
	 * @param user The user to filter the repository by.
	 * @return double This returns the sum of expenses within a range of dates for a given user.
	 */
	@Query("select SUM(e.cost) from Expense e where :startDate <= e.date AND :endDate >= e.date AND e.category.user = :user")
	double getTotalCostBetweenTwoDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("user") User user);

	/**
	 * This method is used to get the first date for any expense for a given user.
	 * 
	 * @param user The user to filter the repository by.
	 * @return double This returns the the first date for any expense for a given user.
	 */
	@Query("SELECT MIN(e.date) FROM Expense e where e.category.user = :user")
	LocalDate getEarliestDate(@Param("user") User user);

	/**
	 * This method is used to get the total of expenses within a range of dates for a given Category.
	 * 
	 * @param startDate The first date of the range.
	 * @param endDate The last date of the range.
	 * @param category The category to filter the repository by.
	 * @param user The user to filter the repository by.
	 * @return double This returns the sum of expenses within a range of dates for a given user.
	 */
	@Query("select SUM(e.cost) from Expense e where :startDate <= e.date AND :endDate >= e.date AND e.category = :category AND e.category.user = :user")
	double getTotalCostBetweenTwoDatesByCategory(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Category category, @Param("user") User user);

	/**
	 * This method is used to get the save an expense entry.
	 * 
	 * @param expense The expense to save.
	 * @return expense This returns the new expense.
	 */
	Expense saveAndFlush(Expense expense);
	
}
