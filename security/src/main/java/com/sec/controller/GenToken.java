package com.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sec.helper.JwtUtil;
import com.sec.model.JwtRequest;
import com.sec.model.JwtResponse;
import com.sec.service.CustomUerDetailService;

@RestController
public class GenToken {
	
	@Autowired
	private CustomUerDetailService customerDetailService;
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@RequestMapping(value="/token",method = RequestMethod.POST)
	public ResponseEntity<?> getToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println("heloo***********************");
		System.out.println(jwtRequest);
		
		try {
		authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		 }
		catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credetials");
			
		}
		UserDetails userdetails=  customerDetailService.loadUserByUsername(jwtRequest.getUsername());
		
		String token = jwtutil.generateToken(userdetails);
		
	    System.out.println("*******" + token);
		
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
