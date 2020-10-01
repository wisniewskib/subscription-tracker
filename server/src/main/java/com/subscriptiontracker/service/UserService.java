package com.subscriptiontracker.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.subscriptiontracker.entity.User;
import com.subscriptiontracker.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found - "+username);
		}
		return createSpringUserFromEntity(user);
	}
	
	private org.springframework.security.core.userdetails.User createSpringUserFromEntity(User user) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());
	}
	
	public void createUser(User user) {
		userRepository.save(user);
	}

}
