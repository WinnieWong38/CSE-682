package com.example.CSE682.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/Auth")
public class AuthController {

	@GetMapping("/getUsername")
	 public String currentUserName() {
	     return SecurityContextHolder.getContext().getAuthentication().getName();
	  }
}
