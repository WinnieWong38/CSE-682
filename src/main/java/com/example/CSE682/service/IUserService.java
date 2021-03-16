package com.example.CSE682.service;

import java.util.List;

import com.example.CSE682.model.User;

public interface IUserService {

	
	List<User> getAll();
	
	
	User save(User user);

	void deleteById(Long id);
		
	User getUserByUserName(String name);
}
