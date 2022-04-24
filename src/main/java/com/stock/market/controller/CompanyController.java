package com.stock.market.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.market.service.CompanyService;

@Controller
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	@Autowired
	CompanyService companyService;

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerCompany(@RequestBody String requestBody) {
		applicationLog.info("Entering registerCompany Controller");

		companyService.registerCompany(requestBody);

		applicationLog.info("Exiting registerCompany Controller");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@GetMapping(value = "/info/{companycode}")
	public ResponseEntity<String> getCompanyByCompanyCode(@PathVariable("companycode") String companyCode) {
		applicationLog.info("Entering getCompanyByCompanyCode Controller");

		companyService.getCompanyByCompanyCode(companyCode);

		applicationLog.info("Entering getCompanyByCompanyCode Controller");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<String> getAllCompanyDetails() {
		applicationLog.info("Entering getAllCompanyDetails Controller");

		companyService.getAllCompanyDetails();

		applicationLog.info("Entering getAllCompanyDetails Controller");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{companycode}")
	public ResponseEntity<String> deleteCompanyByCompanyCode(@PathVariable("companycode") String companyCode) {
		applicationLog.info("Entering deleteCompanyByCompanyCode Controller");

		companyService.deleteCompanyByCompanyCode(companyCode);

		applicationLog.info("Entering deleteCompanyByCompanyCode Controller");
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
