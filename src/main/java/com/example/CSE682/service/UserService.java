package com.example.CSE682.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSE682.model.User;
import com.example.CSE682.repository.UserRepository;


@Service
public class UserService implements IUserService{

	@Autowired
	UserRepository userRepository;
	
	
	@Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
	
	@Override
	public User getUserByUserName(String name)
	{
		return userRepository.getUserByUsername(name);
	}
	
	@Override 
	public User save(User user) {
		return userRepository.save(user);
	}	
	
	@Override  
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	
	
}
