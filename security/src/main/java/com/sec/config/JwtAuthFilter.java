package com.sec.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sec.helper.JwtUtil;
import com.sec.service.CustomUerDetailService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jutil;
	
	@Autowired
	private CustomUerDetailService customuserdetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String tokenheader = request.getHeader("Authorization");
		String username = null;
		String jwttoken = null;
		
		if(tokenheader != null && tokenheader.startsWith("Bearer "))
		{
				jwttoken = tokenheader.substring(7);
				System.out.println("%%%%%%%%%%%");
				System.out.println(jwttoken);
				
				
				try {
					username = jutil.extractUsername(jwttoken);
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println("!!!!!!!!!!!!NULL!!!!!!!!!!!!!!!!");
					e.printStackTrace();
				}
				
				UserDetails userdetails = customuserdetails.loadUserByUsername(username);
				if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
				{
					UsernamePasswordAuthenticationToken usernamepasswordauthenticationtoken = new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
					usernamepasswordauthenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationtoken);
				}
				else
				{
					System.out.println("token is not valid!!!");
				}
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

}
