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

	List<Limit> findAll();
	
	@Query("select l from Limit l where l.user = :user and l.isTotal IS FALSE")
	List<Limit> findAllByUser(@Param("user") User user);
	
	Optional<Limit> findById(Long id);
	
	@Query("select l from Limit l where l.limitid = :id")
	Limit getLimitById(@Param("id") Long id);
	
	void deleteById(Long id);

	@Query("select l from Limit l where l.category = :category AND l.user = :user")
	Limit getLimitByIdByCategory(@Param("category") Category category, @Param("user") User user);
	
	@Query("select l from Limit l where l.isTotal IS TRUE AND l.user = :user")
	Limit getTotalLimit(@Param("user") User user);
}
