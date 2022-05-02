package com.stock.market.serviceImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.stock.market.dto.CompanyDto;
import com.stock.market.dto.CompanyDtoBuilder;
import com.stock.market.dto.CompanyResponse;
import com.stock.market.entity.CompanyDao;
import com.stock.market.repository.CompanyRepository;
import com.stock.market.service.CompanyService;

/**
 * The Class CompanyServiceImpl.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	/** The application log. */
	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	/** The error log. */
	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	/** The company repository. */
	@Autowired
	CompanyRepository companyRepository;

	/** The eureka client. */
	@Autowired
	EurekaClient eurekaClient;

	/**
	 * Register company.
	 *
	 * @param requestBody the request body
	 * @return the boolean
	 * @throws Exception the exception
	 */
	@Override
	public Integer registerCompany(String requestBody) throws Exception {
		applicationLog.info("Entering registerCompany Service");
		Integer isSuccessful = null;
		CompanyDto companyDto = null;

		try {
			companyDto = new ObjectMapper().readValue(requestBody, CompanyDto.class);
		} catch (JsonProcessingException e) {
			errorLog.error("Error in mapping requestbody to company: {}", e.getMessage());
			throw e;
		}

		CompanyDao comapanyDao = companyRepository.findByCompanyCode(companyDto.getCompanyCode());
		if (!validateCompanyFields(companyDto)) {
			applicationLog.info("Field Validation Failed");
			isSuccessful = 3;
		} else if (comapanyDao != null) {
			applicationLog.info("Company Already Exists");
			isSuccessful = 1;
		} else if (Double.parseDouble(companyDto.getCompanyTurnover()) < Double.valueOf(100000000)) {
			applicationLog.info("Company turnover must be greater than 10Cr");
			isSuccessful = 2;
		} else {
			CompanyDao company = new CompanyDao();
			company.setCompanyCode(companyDto.getCompanyCode());
			company.setCompanyName(companyDto.getCompanyName());
			company.setCompanyCeo(companyDto.getCompanyCeo());
			company.setCompanyTurnover(companyDto.getCompanyTurnover());
			company.setCompanyWebsite(companyDto.getCompanyWebsite());
			company.setStockExchange(companyDto.getStockExchange());
			try {
				companyRepository.save(company);
				isSuccessful = 0;
			} catch (Exception e) {
				errorLog.error("Error in saving company details to db. error: {}", e.getMessage());
				throw e;
			}

		}

		applicationLog.info("Exiting registerCompany Service");
		return isSuccessful;
	}

	/**
	 * Gets the company by company code.
	 *
	 * @param companyCode the company code
	 * @return the company by company code
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CompanyDto getCompanyByCompanyCode(String companyCode) throws Exception {
		applicationLog.info("Entering getCompanybyCompanyCode Service");

		CompanyDao company = companyRepository.findByCompanyCode(companyCode);
		applicationLog.debug("company: {}", company);

		CompanyDto companyDto = convertCompanyDaoToDto(company);

		RestTemplate restTemplate = new RestTemplate();
		InstanceInfo instance = getStockServiceInstance();
		String url = "http://" + instance.getHostName() + ":" + instance.getPort()
				+ "/api/v1.0/market/stock/get/stockPrice/";
		ResponseEntity<CompanyResponse> response = null;
		try {
			response = restTemplate.getForEntity(url + company.getCompanyCode(), CompanyResponse.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				CompanyResponse<Double> companyResponse = response.getBody();
				if (companyResponse.getMessage().getCode().equals("LATEST_STOCK_PRICE_FETCHED")) {
					companyDto.setLatestStockPrice(companyResponse.getData());
				}
			}
		} catch (Exception e) {
			errorLog.error("No stock found for company with company code: {}: {}", company.getCompanyCode(),
					e.getMessage());
		}

		applicationLog.info("Exiting getCompanybyCompanyCode Service");
		return companyDto;
	}

	/**
	 * Gets the all company details.
	 *
	 * @return the all company details
	 * @throws Exception the exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CompanyDto> getAllCompanyDetails() throws Exception {
		applicationLog.info("Entering getAllCompanyDetails Service");

		List<CompanyDao> companies = companyRepository.findAll();

		RestTemplate restTemplate = new RestTemplate();
		InstanceInfo instance = getStockServiceInstance();
		String url = "http://" + instance.getHostName() + ":" + instance.getPort()
				+ "/api/v1.0/market/stock/get/stockPrice/";

		List<CompanyDto> companyDtos = new ArrayList<CompanyDto>();
		for (CompanyDao company : companies) {
			CompanyDto companyDto = convertCompanyDaoToDto(company);

			applicationLog.info("Connecting to : {}", (url + company.getCompanyCode()));
			ResponseEntity<CompanyResponse> response = null;
			try {
				response = restTemplate.getForEntity(url + company.getCompanyCode(), CompanyResponse.class);
				if (response.getStatusCode().is2xxSuccessful()) {
					CompanyResponse<Double> companyResponse = response.getBody();
					if (companyResponse.getMessage().getCode().equals("LATEST_STOCK_PRICE_FETCHED")) {
						companyDto.setLatestStockPrice(companyResponse.getData());
					}
				}
			} catch (Exception e) {
				errorLog.error("No stock found for company with company code: {}", company.getCompanyCode());
			}

			companyDtos.add(companyDto);
		}

		applicationLog.info("Exiting getAllCompanyDetails Service");
		return companyDtos;
	}

	/**
	 * Delete company by company code.
	 *
	 * @param companyCode the company code
	 * @return the boolean
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Boolean deleteCompanyByCompanyCode(String companyCode) throws Exception {
		applicationLog.info("Entering deleteCompanyByCompanyCode Service");
		Boolean isSuccessful = true;

		CompanyDao company = companyRepository.findByCompanyCode(companyCode);
		companyRepository.delete(company);

		RestTemplate restTemplate = new RestTemplate();
		InstanceInfo instance = getStockServiceInstance();
		String url = "http://" + instance.getHostName() + ":" + instance.getPort() + "/api/v1.0/market/stock/delete/";

		ResponseEntity<CompanyResponse> response = null;
		try {
			response = restTemplate.exchange(url + companyCode, HttpMethod.DELETE, null, CompanyResponse.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				applicationLog.info("Stocks deleted for company with companycode: {}", company.getCompanyCode());
			}
		} catch (Exception e) {
			errorLog.error("Error in deleting stocks for company with companycode: {}: [{}]", company.getCompanyCode(),
					e.getMessage());
		}

		applicationLog.info("Exiting deleteCompanyByCompanyCode Service");
		return isSuccessful;
	}

	/**
	 * Validate company fields.
	 *
	 * @param company the company
	 * @return the boolean
	 */
	private Boolean validateCompanyFields(CompanyDto company) {

		try {
			Double.parseDouble(company.getCompanyTurnover());
		} catch (Exception e) {
			return false;
		}

		try {
			new URL(company.getCompanyWebsite());
		} catch (MalformedURLException e) {
			applicationLog.info("Company website is not a url: {}", e.getMessage());
			return false;
		}

		if (stringNullAndEmptyValidation(company.getCompanyCeo())
				&& stringNullAndEmptyValidation(company.getCompanyCode())
				&& stringNullAndEmptyValidation(company.getCompanyName())
				&& stringNullAndEmptyValidation(company.getCompanyTurnover())
				&& stringNullAndEmptyValidation(company.getCompanyWebsite())
				&& stringNullAndEmptyValidation(company.getStockExchange())) {
			applicationLog.info("Field validation successful");
			return true;
		}

		return false;
	}

	/**
	 * String null and empty validation.
	 *
	 * @param value the value
	 * @return the boolean
	 */
	private Boolean stringNullAndEmptyValidation(String value) {
		if (value != null && !value.isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * Convert company dao to dto.
	 *
	 * @param dao the dao
	 * @return the company dto
	 */
	private CompanyDto convertCompanyDaoToDto(CompanyDao dao) {
		CompanyDto dto = new CompanyDtoBuilder().setCompanyId(dao.getCompanyId()).setCompanyCeo(dao.getCompanyCeo())
				.setCompanyCode(dao.getCompanyCode()).setCompanyName(dao.getCompanyName())
				.setCompanyTurnover(dao.getCompanyTurnover()).setCompanyWebsite(dao.getCompanyWebsite())
				.setStockExchange(dao.getStockExchange()).build();
		return dto;
	}

	/**
	 * Gets the stock service instance.
	 *
	 * @return the stock service instance
	 */
	private InstanceInfo getStockServiceInstance() {
		List<InstanceInfo> instances = eurekaClient.getApplication("stock-service").getInstances();
		Integer randomIndex = new Random().nextInt(instances.size());
		InstanceInfo instance = instances.get(randomIndex);
		return instance;
	}
}
