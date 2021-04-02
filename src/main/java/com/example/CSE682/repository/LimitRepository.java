package com.example.CSE682.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import com.example.CSE682.model.Limit;
import com.example.CSE682.model.User;
import com.example.CSE682.model.Category;

public interface LimitRepository extends CrudRepository<Limit, Long>{

	/**
	 * This method is used to get all the Limits in the repository.
	 * 
	 * @return List<Limit> This returns the Limits in the repository.
	 */
	List<Limit> findAll();
	
	/**
	 * This method is used to get all the Limits for a given user.
	 * 
	 * @param user The user whose categories should be returned.
	 * @return List<Limit> This returns the Limits for a given user.
	 */
	@Query("select l from Limit l where l.user = :user and l.isTotal IS FALSE")
	List<Limit> findAllByUser(@Param("user") User user);
	
	/**
	 * This method is used to get an Limit for a given id.
	 * 
	 * @param id The id of the Limit.
	 * @return Optional<Limit> This returns the Limit for a given id.
	 */
	Optional<Limit> findById(Long id);
	
	/**
	 * This method is used to get a Limit for a given id.
	 * 
	 * @param id The id of the Limit.
	 * @return Limit This returns the Limit for a given id.
	 */
	@Query("select l from Limit l where l.limitid = :id")
	Limit getLimitById(@Param("id") Long id);

	/**
	 * This method is used to remove an Limit.
	 * 
	 * @param id The ID of the Limit.
	 */
	void deleteById(Long id);
	
	/**
	 * This method is used to get a Limit for a given id.
	 * 
	 * @param category The category whose Limit should be returned.
	 * @param user The User who made the Limit.
	 * @return Limit This returns the Limit for a given Category.
	 */
	@Query("select l from Limit l where l.category = :category AND l.user = :user")
	Limit getLimitByIdByCategory(@Param("category") Category category, @Param("user") User user);
	
	/**
	 * This method is used to get a total Limit for a given user.
	 * 
	 * @param user The User who made the Limit.
	 * @return Limit This returns the total Limit for a given user.
	 */
	@Query("select l from Limit l where l.isTotal IS TRUE AND l.user = :user")
	Limit getTotalLimit(@Param("user") User user);
}
