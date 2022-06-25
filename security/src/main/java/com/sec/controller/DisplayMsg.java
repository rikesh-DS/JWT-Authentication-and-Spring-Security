package com.sec.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayMsg {

	@RequestMapping("/welcome")
	public String welcome()
	{
	return "welocme@@@@";
	}
}
