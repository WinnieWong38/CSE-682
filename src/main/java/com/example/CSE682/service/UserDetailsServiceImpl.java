package com.example.CSE682.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.CSE682.model.Income;
import com.example.CSE682.model.User;
import com.example.CSE682.repository.UserRepository;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
    
    public User save(User user) {
		return userRepository.save(user);
	}
  
    public Long registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
    //    final Role USER_ROLE = roleService.findByAuthority(RoleEnum.USER);
    //    user.setAuthorities(Collections.singletonList(USER_ROLE));
 
        userRepository.save(user);
        return user.getid();
    }
   
 
}