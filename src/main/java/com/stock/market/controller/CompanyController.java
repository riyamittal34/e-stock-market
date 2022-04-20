package com.stock.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

	@GetMapping(value = "/test")
	public ResponseEntity<String> testController() {
		
		System.out.println("TestSuccess");
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
}
