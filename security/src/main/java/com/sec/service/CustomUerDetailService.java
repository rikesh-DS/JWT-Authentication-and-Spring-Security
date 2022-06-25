package com.sec.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUerDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if (username.equals("Rikesh"))
		{
		return new User("Rikesh", "Password", new ArrayList<>());
		}
		else
		{
			throw new UsernameNotFoundException("Username not found!!!");
		}
	}


}
