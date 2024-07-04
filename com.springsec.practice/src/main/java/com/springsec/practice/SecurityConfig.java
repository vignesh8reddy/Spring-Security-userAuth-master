package com.springsec.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
	
	@Bean
	public UserDetailsService myDetailsService() {
		UserDetails user=new User("nikhil", passwordEncoder().encode("nikhil"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
		UserDetails admin=new User("admin",passwordEncoder().encode("admin"),Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//		System.err.println(user.getPassword());
		return new InMemoryUserDetailsManager(user,admin);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.authorizeHttpRequests().requestMatchers("/user").hasRole("USER").requestMatchers("/admin").hasRole("ADMIN").and().formLogin();
		 return http.build();
		
	}
}
