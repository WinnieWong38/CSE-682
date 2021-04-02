package com.example.CSE682.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.CSE682.model.User;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;

	/**
	 * This method is used to construct a MyUserDetails object.
	 */
	public MyUserDetails(User user) {
		this.user = user;
	}

	/**
	 * This method is used to get the authorities of a user.
	 * 
	 * @return Collection<? extends GrantedAuthority> The authorities of the user.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(authority);
	}

	/**
	 * This method is used to get the password.
	 * 
	 * @return String This returns the password.
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * This method is used to get the user name.
	 * 
	 * @return String This returns the user name.
	 */
	@Override
	public String getUsername() {
		return user.getUserName();
	}

	/**
	 * This method is not yet implemented.
	 * 
	 * @return boolean True.
	 */
	@Override
	public boolean isAccountNonExpired() {
		// Function not implemented, always true
		return true;
	}

	/**
	 * This method is not yet implemented.
	 * 
	 * @return boolean True.
	 */
	@Override
	public boolean isAccountNonLocked() {
		// Function not implemented, always true
		return true;
	}

	/**
	 * This method is not yet implemented.
	 * 
	 * @return boolean True.
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// Function not implemented, always true
		return true;
	}

	/**
	 * This method is not yet implemented.
	 * 
	 * @return boolean True.
	 */
	@Override
	public boolean isEnabled() {
		// Function not implemented, always true
		return true;
	}
}