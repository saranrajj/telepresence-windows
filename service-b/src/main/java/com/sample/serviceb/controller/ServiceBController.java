package com.sample.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

	
	@GetMapping("/greet")
	public String sayHello() {
		return "Hello from service B";
	}
	
	
	@GetMapping("/sayproverb")
	public String sayProverb() {
		return "Something is better than nothing!!!";
	}
	
	@GetMapping("/saysomething")
	public String saySomething(){
		return "some thing!!!";
	}
}
