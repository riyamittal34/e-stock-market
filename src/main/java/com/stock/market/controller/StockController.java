package com.stock.market.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.market.service.StockService;

@Controller
@RequestMapping("/api/v1.0/market/stock")
public class StockController {
	
	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");
	
	@Autowired
	StockService stockService;

	@PostMapping(value = "/add/{companycode}")
	public ResponseEntity<String> addCompanyStock(@PathVariable("companycode") String companyCode,
			@RequestBody String requestBody) {
		applicationLog.info("Entering registerCompany Controller");
		
		stockService.addCompanyStock(companyCode, requestBody);

		applicationLog.info("Entering registerCompany Controller");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@GetMapping(value = "get/{companycode}/{startdate}/{enddate}")
	public ResponseEntity<String> filterStocks(@PathVariable("companycode") String companyCode,
			@PathVariable("startdate") Date startDate, @PathVariable("enddate") Date endDate) {
		applicationLog.info("Entering registerCompany Controller");
		
		stockService.filterStocks(companyCode, startDate, endDate);
		
		applicationLog.info("Entering registerCompany Controller");
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
