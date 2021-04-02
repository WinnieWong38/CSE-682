package com.example.CSE682.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.CSE682.model.Category;
import com.example.CSE682.model.User;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	/**
	 * This method is used to get all the Categories in the repository.
	 * 
	 * @return List<Category> This returns the Categories in the repository.
	 */
	List<Category> findAll();
	
	/**
	 * This method is used to get all the Categories for a given user.
	 * 
	 * @param user The user whose categories should be returned.
	 * @return List<Category> This returns the Categories for a given user.
	 */
	@Query("select c from Category c where c.user = :user")
	List<Category> findAllByUser(@Param("user") User user);
	
	/**
	 * This method is used to get a Category for a given id.
	 * 
	 * @param id The id of the Category.
	 * @return Category This returns the Category for a given id.
	 */
	@Query("select c from Category c where c.categoryid = :id")
	Category getCategoryById(@Param("id") Long id);
	
	/**
	 * This method is used to get a Category for a given id.
	 * 
	 * @param id The id of the Category.
	 * @return Optional<Category> This returns the Category for a given id.
	 */
	Optional<Category> findById(Long id);
		
}
