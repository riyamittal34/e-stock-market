package com.stock.market.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stock.market.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	@Override
	public String registerCompany(String requestBody) {
		return null;
	}

	@Override
	public String getCompanyByCompanyCode(String companyCode) {
		return null;
	}

	@Override
	public List<String> getAllCompanyDetails() {
		return null;
	}

	@Override
	public String deleteCompanyByCompanyCode(String companyCode) {
		return null;
	}

}
