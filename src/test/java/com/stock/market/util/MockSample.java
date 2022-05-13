package com.stock.market.util;

import java.util.UUID;

import com.stock.market.dto.CompanyDto;
import com.stock.market.entity.CompanyDao;

/**
 * The Class MockSample.
 */
public class MockSample {

	/**
	 * Gets the company object.
	 *
	 * @return the company object
	 */
	public static CompanyDto getCompanyObject() {
		CompanyDto company = new CompanyDto();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		company.setCompanyCeo("Riya");
		company.setCompanyTurnover("1000000000");
		company.setCompanyWebsite("http://www.google.com");
		company.setStockExchange("NSE");
		return company;
	}
	
	/**
	 * Gets the company dao object.
	 *
	 * @return the company dao object
	 */
	public static CompanyDao getCompanyDaoObject() {
		CompanyDao company = new CompanyDao();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		company.setCompanyCeo("Riya");
		company.setCompanyTurnover("1000000000");
		company.setCompanyWebsite("http://www.google.com");
		company.setStockExchange("NSE");
		return company;
	}

}
