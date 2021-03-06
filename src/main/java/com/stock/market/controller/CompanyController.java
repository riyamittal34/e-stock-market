package com.stock.market.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stock.market.constants.CompanyConstants;
import com.stock.market.dto.CompanyDto;
import com.stock.market.dto.CompanyResponse;
import com.stock.market.dto.ResponseMessage;
import com.stock.market.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * The Class CompanyController.
 */
@RestController
public class CompanyController {

	/** The application log. */
	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	/** The error log. */
	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	/** The company service. */
	@Autowired
	CompanyService companyService;

	/**
	 * Register company.
	 *
	 * @param companyDto the company dto
	 * @return the response entity
	 */
	@PostMapping(value = "/register")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CompanyResponse<Boolean>> registerCompany(@RequestBody CompanyDto companyDto) {
		applicationLog.info("Entering registerCompany Controller");
		Integer isSuccessful = null;
		CompanyResponse<Boolean> response = new CompanyResponse<Boolean>();
		ResponseMessage message = new ResponseMessage();
		try {
			isSuccessful = companyService.registerCompany(companyDto);
			if (isSuccessful == 2) {
				message.setCode(CompanyConstants.COMPANY_TURNOVER_IS_LESS);
				message.setDescription("Company turnover should be greater than 10Cr");
				response.withData(false);
			} else if (isSuccessful == 1) {
				message.setCode(CompanyConstants.COMPANY_ALREADY_EXIST);
				message.setDescription("Company already exists");
				response.withData(false);
			} else if (isSuccessful == 3) {
				message.setCode(CompanyConstants.FIELD_VALIDATION_FAILD);
				message.setDescription("Field validation failed");
				response.withData(false);
			} else {
				message.setCode(CompanyConstants.COMPANY_REGISTERED);
				message.setDescription("Company registered");
				response.withData(true);
			}
			response.withMessage(message);
			applicationLog.info(CompanyConstants.EXITING_REGISTER_COMPANY_CONTROLLER);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			errorLog.error("Error in Registering the company. error: [{}]", e.getMessage());
			response.withData(false);
			message.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			message.setDescription("Error in Registering the company");
			response.withMessage(message);
			applicationLog.info(CompanyConstants.EXITING_REGISTER_COMPANY_CONTROLLER);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the company by company code.
	 *
	 * @param companyCode the company code
	 * @return the company by company code
	 */
	@GetMapping(value = "/info/{companycode}")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CompanyResponse<CompanyDto>> getCompanyByCompanyCode(
			@PathVariable("companycode") String companyCode) {
		applicationLog.info("Entering getCompanyByCompanyCode Controller");
		CompanyDto company = null;
		CompanyResponse<CompanyDto> response = new CompanyResponse<CompanyDto>();
		ResponseMessage message = new ResponseMessage();
		try {
			company = companyService.getCompanyByCompanyCode(companyCode);
			applicationLog.info(CompanyConstants.EXITING_GET_COMPANY_BY_CODE_CONTROLLER);
			if (company == null) {
				message.setCode(CompanyConstants.COMPANY_NOT_FOUND);
				message.setDescription("No company found with company code: " + companyCode);
				response.withData(company);
				response.withMessage(message);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			} else {
				message.setCode(CompanyConstants.COMPANY_FOUND);
				message.setDescription("Company found with the company code: " + companyCode);
				response.withData(company);
				response.withMessage(message);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			errorLog.error("Error in fetching company details for company code: {}, error: {}", companyCode,
					e.getMessage());
			message.setCode(CompanyConstants.INTERNAL_SERVER_ERROR);
			message.setDescription("Error in fetching company with company code: " + companyCode);
			response.withData(company);
			response.withMessage(message);
			applicationLog.info(CompanyConstants.EXITING_GET_COMPANY_BY_CODE_CONTROLLER);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the all company details.
	 *
	 * @return the all company details
	 */
	@GetMapping(value = "/getall")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CompanyResponse<List<CompanyDto>>> getAllCompanyDetails() {
		applicationLog.info("Entering getAllCompanyDetails Controller");
		CompanyResponse<List<CompanyDto>> response = new CompanyResponse<>();
		List<CompanyDto> companies = null;
		ResponseMessage message = new ResponseMessage();
		try {
			companies = companyService.getAllCompanyDetails();
			if (companies != null && companies.size() > 0) {
				message.setCode(CompanyConstants.DATA_FETCH_SUCCESS);
				message.setDescription("Fetching all company details");
				response.withData(companies);
				response.withMessage(message);
				applicationLog.info(CompanyConstants.EXITING_ALL_COMPANY_CONTROLLER);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				message.setCode(CompanyConstants.NO_COMPANY_FOUND);
				message.setDescription("No data found");
				response.withData(companies);
				response.withMessage(message);
				applicationLog.info(CompanyConstants.EXITING_ALL_COMPANY_CONTROLLER);
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			errorLog.error("Error in fetch all company details. error: [{}]", e.getMessage());
			message.setCode(CompanyConstants.INTERNAL_SERVER_ERROR);
			message.setDescription("Error in fetching all company data");
			response.withData(companies);
			response.withMessage(message);
			applicationLog.info(CompanyConstants.EXITING_ALL_COMPANY_CONTROLLER);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete company by company code.
	 *
	 * @param companyCode the company code
	 * @return the response entity
	 */
	@DeleteMapping(value = "/delete/{companycode}")
	@Operation(security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<CompanyResponse<Boolean>> deleteCompanyByCompanyCode(
			@PathVariable("companycode") String companyCode) {
		applicationLog.info("Entering deleteCompanyByCompanyCode Controller");
		CompanyResponse<Boolean> response = new CompanyResponse<>();
		ResponseMessage message = new ResponseMessage();
		try {
			Boolean isSuccessful = companyService.deleteCompanyByCompanyCode(companyCode);
			response.withData(isSuccessful);
			message.setCode(CompanyConstants.COMPANY_DELETED);
			message.setDescription("Company with code - " + companyCode + " is deleted");
			response.withMessage(message);
			applicationLog.info(CompanyConstants.EXITING_DELETE_COMPANY_BY_CODE_CONTROLLER);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			errorLog.error("Error in deleting company with company code: [{}], error: [{}]", companyCode,
					e.getMessage());
			message.setCode(CompanyConstants.INTERNAL_SERVER_ERROR);
			message.setDescription("Error in deleting company with company code: " + companyCode);
			response.withData(false);
			response.withMessage(message);
			applicationLog.info(CompanyConstants.EXITING_DELETE_COMPANY_BY_CODE_CONTROLLER);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
