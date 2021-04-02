package com.example.CSE682.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CSE682.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	/**
	 * This method is used to get all the Users in the repository.
	 * 
	 * @return List<User> This returns the User in the repository.
	 */
	List<User> findAll();

	/**
	 * This method is used to get the ID of a user with a given username.
	 * 
	 * @return Long This returns the ID of a user in the repository.
	 */
	@Query("select U.id from User U where U.username = :username")
	Long getIdByUserName(String username);

	/**
	 * This method is used to get a user with a given username.
	 * 
	 * @return User This returns a user in the repository.
	 */
	@Query("select U from User U where U.username = :username")
	User getUserByUsername(@Param("username") String username);

	/**
	 * This method is used to delete a user.
	 * 
	 * @param id The ID of the user to delete.
	 */
	void deleteById(Long id);
}
