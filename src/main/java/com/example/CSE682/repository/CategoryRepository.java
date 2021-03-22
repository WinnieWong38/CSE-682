package com.example.CSE682.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.CSE682.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	List<Category> findAll();
	
	@Query("select c from Category c where c.categoryid = :id")
	Category getCategoryById(@Param("id") Long id);
	
	Optional<Category> findById(Long id);
}
