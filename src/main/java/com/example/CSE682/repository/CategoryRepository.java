package com.example.CSE682.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.CSE682.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	List<Category> findAll();
	
	Optional<Category> findById(Long id);
}
