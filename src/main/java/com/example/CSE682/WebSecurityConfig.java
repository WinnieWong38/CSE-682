package com.example.CSE682;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.CSE682.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        
		//need to change to DB
		auth.inMemoryAuthentication()
		.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
        .and()
        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }
	*/
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
	//.antMatchers("/api/**","/css/**", "/js/**", "/images/**").permitAll()
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable();
		
		http
			.authorizeRequests()		
			.antMatchers("/login.html","/js/**", "/api/**", "/css/**","/register.html", "/assets/**").permitAll()		    
		    .antMatchers("/index.html","/income.html").authenticated()
		    .anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login.html")				
			      .defaultSuccessUrl("/index.html", true)			      
			  //    .failureHandler(authenticationFailureHandler())
			      .and()
			      .logout()
		//	      .logoutUrl("/perform_logout")
			      .deleteCookies("JSESSIONID");
			//      .logoutSuccessHandler(logoutSuccessHandler());
	}

	/*@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	*/
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
}
