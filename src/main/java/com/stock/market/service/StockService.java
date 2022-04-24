package com.stock.market.service;

import java.util.Date;

public interface StockService {

	public String addCompanyStock(String companyCode, String requestBody);
	
	public String filterStocks(String companyCode, Date startDate, Date endDate);
}
