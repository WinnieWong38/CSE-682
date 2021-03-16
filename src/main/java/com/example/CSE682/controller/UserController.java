package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CSE682.model.User;
import com.example.CSE682.service.IUserService;

@RestController
@RequestMapping("/api/User")
public class UserController {

		
	@Autowired
	IUserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	/*@GetMapping("/getUsers")
    public List<UserAccount> getUsers() {
		return userDetails.getAll();
    }
	
	/*@PostMapping("/addUser")
	public UserAccount addUser(@RequestBody UserAccount user){
		return userService.save(user);
	}
	*/
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user){
		//encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.save(user);
		//UserAccount t = userService.save(user);
		//userDetails.registerNewUser(t); //change password
		
	}
	
	
	
	//PutMapping - for edit
	
	//DeleteMapping - for delete
/*	@DeleteMapping("/deleteVal/{id}")
	  void deleteLimit(@PathVariable Long id) {
		userService.delete(id);
	  }
	
	@PostMapping("/checkUser")
	public boolean addUser(@RequestBody String user,@RequestBody String pwd){
		return userService.checkUser(user, pwd);
	}
	
	@GetMapping("/getIdByUserName/{username}")
	public Long GetIdByUserName(@PathVariable String username)
	{
		return userService.getIdByUserName(username);
	}
	*/
}
