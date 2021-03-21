package com.example.CSE682.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.CSE682.model.User;
import com.example.CSE682.repository.UserRepository;


@Service
public class UserService implements IUserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
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

	@Override
	public boolean checkPwd(String username, String password)
	{
		User userData = userRepository.getUserByUsername(username); //get the user object from the repository
		
		return passwordEncoder.matches(password, userData.getPassword());	//check to see if the password matches the database	
		
	}
	
	@Override
	public boolean changePwd(String username, String newPassword)
	{
		User userData = userRepository.getUserByUsername(username); //get the user object from the repository
		
		userData.setPassword(passwordEncoder.encode(newPassword)); //change the password in the user object
		
		userRepository.save(userData); //update data in db
		
		return true; //hook for future error checking
	}
	
}
