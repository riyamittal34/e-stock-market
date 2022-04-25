package com.stock.market.controller;

import java.util.List;

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

import com.stock.market.dto.CompanyResponse;
import com.stock.market.dto.ResponseMessage;
import com.stock.market.entity.CompanyDao;
import com.stock.market.service.CompanyService;

@Controller
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	@Autowired
	CompanyService companyService;

	@PostMapping(value = "/register")
	public ResponseEntity<CompanyResponse<Boolean>> registerCompany(@RequestBody String requestBody) {
		applicationLog.info("Entering registerCompany Controller");
		Boolean isSuccessful = false;
		CompanyResponse<Boolean> response = new CompanyResponse<Boolean>();
		ResponseMessage message = new ResponseMessage();
		try {
			isSuccessful = companyService.registerCompany(requestBody);
			if (isSuccessful) {
				message.setCode("COMPANY_REGISTERED");
				message.setDescription("Company registered");
			} else {
				message.setCode("COMPANY_ALREADY_EXIST");
				message.setDescription("Company already exists");
			}
			response.withMessage(message);
			response.withData(isSuccessful);
			applicationLog.info("Exiting registerCompany Controller");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			errorLog.error("Error in Registering the company. error: [{}]", e.getMessage());
			response.withData(false);
			message.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			message.setDescription("Error in Registering the company");
			response.withMessage(message);
			applicationLog.info("Exiting registerCompany Controller");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/info/{companycode}")
	public ResponseEntity<CompanyResponse<CompanyDao>> getCompanyByCompanyCode(
			@PathVariable("companycode") String companyCode) {
		applicationLog.info("Entering getCompanyByCompanyCode Controller");
		CompanyDao company = null;
		CompanyResponse<CompanyDao> response = new CompanyResponse<CompanyDao>();
		ResponseMessage message = new ResponseMessage();
		try {
			company = companyService.getCompanyByCompanyCode(companyCode);
			applicationLog.info("Entering getCompanyByCompanyCode Controller");
			if (company == null) {
				message.setCode("COMPANY_NOT_FOUND");
				message.setDescription("No company found with company code: " + companyCode);
				response.withData(company);
				response.withMessage(message);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				message.setCode("COMANY_FOUND");
				message.setDescription("Company found with the company code: " + companyCode);
				response.withData(company);
				response.withMessage(message);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			errorLog.error("Error in fetching company details for company code: {}, error: {}", companyCode,
					e.getMessage());
			message.setCode("INTERNAL_SERVER_ERROR");
			message.setDescription("Error in fetching company with company code: " + companyCode);
			response.withData(company);
			response.withMessage(message);
			applicationLog.info("Entering getCompanyByCompanyCode Controller");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<CompanyResponse<List<CompanyDao>>> getAllCompanyDetails() {
		applicationLog.info("Entering getAllCompanyDetails Controller");
		CompanyResponse<List<CompanyDao>> response = new CompanyResponse<>();
		List<CompanyDao> companies = null;
		ResponseMessage message = new ResponseMessage();
		try {
			companies = companyService.getAllCompanyDetails();
			if (companies != null && companies.size() > 0) {
				message.setCode("DATA_FETCH_SUCCESS");
				message.setDescription("Fetching all company details");
				response.withData(companies);
				response.withMessage(message);
				applicationLog.info("Entering getAllCompanyDetails Controller");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				message.setCode("NO_COMPANY_FOUND");
				message.setDescription("No data found");
				response.withData(companies);
				response.withMessage(message);
				applicationLog.info("Entering getAllCompanyDetails Controller");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			errorLog.error("Error in fetch all company details. error: [{}]", e.getMessage());
			message.setCode("INTERNAL_SERVER_ERROR");
			message.setDescription("Error in fetching all company data");
			response.withData(companies);
			response.withMessage(message);
			applicationLog.info("Entering getAllCompanyDetails Controller");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete/{companycode}")
	public ResponseEntity<CompanyResponse<Boolean>> deleteCompanyByCompanyCode(
			@PathVariable("companycode") String companyCode) {
		applicationLog.info("Entering deleteCompanyByCompanyCode Controller");
		CompanyResponse<Boolean> response = new CompanyResponse<>();
		ResponseMessage message = new ResponseMessage();
		try {
			Boolean isSuccessful = companyService.deleteCompanyByCompanyCode(companyCode);
			response.withData(isSuccessful);
			message.setCode("COMPANY_DELETED");
			message.setDescription("Company with code - " + companyCode + " is deleted");
			response.withMessage(message);
			applicationLog.info("Entering deleteCompanyByCompanyCode Controller");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			errorLog.error("Error in deleting company with company code: [{}], error: [{}]", companyCode,
					e.getMessage());
			message.setCode("INTERNAL_SERVER_ERROR");
			message.setDescription("Error in deleting company with company code: " + companyCode);
			response.withData(false);
			response.withMessage(message);
			applicationLog.info("Entering deleteCompanyByCompanyCode Controller");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
