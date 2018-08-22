package com.sample.servicea.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceAController {
	
	private final String SERVICE_B_SAY_PROVERB_URL= "http://service-b:8080/sayproverb";
	
	RestTemplate restTemplate=new RestTemplate();
	
	
	@GetMapping("/greet")
	public String sayHello() {		
		return "Hello from Service A";
	}
	
	@GetMapping("/sayproverb")
	public ResponseEntity sayProverb() {
		try {
		ResponseEntity<String> response= restTemplate.getForEntity(SERVICE_B_SAY_PROVERB_URL, String.class);
		return response;
		}
		catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}

}
