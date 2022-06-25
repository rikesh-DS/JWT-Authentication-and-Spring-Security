package com.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sec.service.CustomUerDetailService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthFilter JwtFilter;
	
	@Autowired
	private CustomUerDetailService customuserdetailservice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customuserdetailservice);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors().disable().csrf().disable().authorizeRequests().antMatchers("/token").permitAll().anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(JwtFilter,UsernamePasswordAuthenticationFilter.class);
	}
@Bean	
public PasswordEncoder getPasswordEncoder()
{
	return NoOpPasswordEncoder.getInstance();
	
}
@Bean
public AuthenticationManager authenticationManagerBean() throws Exception
{
	
return super.authenticationManagerBean();	
}

}
