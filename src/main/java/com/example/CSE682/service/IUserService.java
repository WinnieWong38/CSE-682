package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.User;

public interface IUserService {

	/**
	 * This method is used to get all the Users in the repository.
	 * 
	 * @return List<User> This returns the User in the repository.
	 */
	List<User> getAll();

	/**
	 * This method is used to save a User into the repository.
	 * 
	 * @param user The user to save.
	 * @return User This returns the saved user.
	 */
	User save(User user);

	/**
	 * This method is used to delete a user.
	 * 
	 * @param id The ID of the user to delete.
	 */
	void deleteById(Long id);

	/**
	 * This method is used to get a user with a given username.
	 * 
	 * @param name The username.
	 * @return User This returns a user in the repository.
	 */
	User getUserByUserName(String name);

	/**
	 * This method is used to get the currently logged in user.
	 * 
	 * @return User This returns the currently logged in user.
	 */
	User getLoggedinUser();

	/**
	 * This method is validate the user's password.
	 * 
	 * @param username The username.
	 * @param password The input password.
	 * @return User This returns true if the password matches the stored password.
	 */
	boolean checkPwd(String username, String password);

	/**
	 * This method is used to change a password.
	 * 
	 * @param username    The username.
	 * @param newPassword The new password.
	 * @return boolean True if the password was changed successfully.
	 */
	boolean changePwd(String username, String newPassword);
}
