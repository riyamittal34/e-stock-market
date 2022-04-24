package com.stock.market.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.market.dto.CompanyDto;
import com.stock.market.entity.CompanyDao;
import com.stock.market.repository.CompanyRepository;
import com.stock.market.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Boolean registerCompany(String requestBody) throws Exception {
		applicationLog.info("Entering registerCompany Service");
		Boolean isSuccessful = false;
		CompanyDto companyDto = null;

		try {
			companyDto = new ObjectMapper().readValue(requestBody, CompanyDto.class);
		} catch (JsonProcessingException e) {
			errorLog.error("Error in mapping requestbody to company: {}", e.getMessage());
			throw e;
		}

		CompanyDao company = new CompanyDao();
		company.setCompanyCode(companyDto.getCompanyCode());
		company.setCompanyName(companyDto.getCompanyName());
		try {
			companyRepository.save(company);
			isSuccessful = true;
		} catch (Exception e) {
			errorLog.error("Error in saving company details to db. error: {}", e.getMessage());
			throw e;
		}
		applicationLog.info("Exiting registerCompany Service");
		return isSuccessful;
	}

	@Override
	public CompanyDao getCompanyByCompanyCode(String companyCode) throws Exception {
		applicationLog.info("Entering getCompanybyCompanyCode Service");

		CompanyDao company = companyRepository.findByCompanyCode(companyCode);
		applicationLog.debug("company: {}", company);

		applicationLog.info("Exiting getCompanybyCompanyCode Service");
		return company;
	}

	@Override
	public List<CompanyDao> getAllCompanyDetails() throws Exception {
		applicationLog.info("Entering getAllCompanyDetails Service");

		List<CompanyDao> companies = companyRepository.findAll();

		applicationLog.info("Exiting getAllCompanyDetails Service");
		return companies;
	}

	@Override
	public Boolean deleteCompanyByCompanyCode(String companyCode) throws Exception {
		applicationLog.info("Entering deleteCompanyByCompanyCode Service");
		Boolean isSuccessful = true;

		CompanyDao company = companyRepository.findByCompanyCode(companyCode);
		companyRepository.delete(company);

		applicationLog.info("Exiting deleteCompanyByCompanyCode Service");
		return isSuccessful;
	}

}
