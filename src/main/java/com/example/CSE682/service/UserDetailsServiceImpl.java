package com.example.CSE682.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.CSE682.model.User;
import com.example.CSE682.repository.UserRepository;
 
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	/**
	 * This method is used to load a User with a given username.
	 * 
	 * @param username The username of the User to load.
	 * @return UserDetails This returns the stored UserDetails of a user.
	 */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }

	/**
	 * This method is used to save a User into the repository.
	 * 
	 * @param user The user to save.
	 * @return User This returns the saved user.
	 */
    public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * This method is used to add a User into the repository.
	 * 
	 * @param user The user to add.
	 * @return Long This returns the ID of the saved user.
	 */
    public Long registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
    //    final Role USER_ROLE = roleService.findByAuthority(RoleEnum.USER);
    //    user.setAuthorities(Collections.singletonList(USER_ROLE));
 
        userRepository.save(user);
        return user.getid();
    }
   
 
}