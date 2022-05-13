package com.stock.market.util;

import java.util.UUID;

import com.stock.market.entity.CompanyDao;

public class MockSample {

	public static CompanyDao getCompanyObject() {
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
