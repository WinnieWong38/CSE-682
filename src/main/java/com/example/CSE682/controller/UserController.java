package com.example.CSE682.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	*/
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user){
		//make sure that only regular users are created with this call for security
		user.setRole("user");
		//encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.save(user);		
	}
	
	//for authentication
	@GetMapping("/getUsername")
	 public String currentUserName() {
	     return SecurityContextHolder.getContext().getAuthentication().getName();
	  }
	
	@PostMapping("/changePwd")
	public boolean changePwd(@RequestParam(name="username") String username, @RequestParam(name="oldPassword") String oldPassword, @RequestParam(name="newPassword") String newPassword)
	{
		//make sure that the old password is valid
		boolean validOldPWD  = userService.checkPwd(username, oldPassword);
		
		if(!validOldPWD) return false; //the old password does not match so return error
		
		//change the password
		 return userService.changePwd(username, newPassword);
	}
	
	@GetMapping("/checkPwd")
	public boolean checkPwd(@RequestParam(name="username") String username, @RequestParam(name="oldPassword") String oldPassword)
	{		
		//make sure that the old password is valid
		return userService.checkPwd(username, oldPassword);		
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
