package com.example.CSE682.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CSE682.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findAll();
	
	@Query("select U.id from User U where U.username = :username")
	Long getIdByUserName(String username);
	
	@Query("select U from User U where U.username = :username")
	User getUserByUsername(@Param("username") String username);
	
	void deleteById(Long id);
}
