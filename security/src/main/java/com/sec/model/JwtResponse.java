package com.sec.model;

public class JwtResponse {
	
	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String token;
	
	public JwtResponse()
	{
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}

