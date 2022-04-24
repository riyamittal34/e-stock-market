package com.stock.market.serviceImpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stock.market.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	private final Logger applicationLog = LoggerFactory.getLogger("[APPLICATION]");

	private final Logger errorLog = LoggerFactory.getLogger("[ERROR]");

	@Override
	public String addCompanyStock(String companyCode, String requestBody) {
		return null;
	}

	@Override
	public String filterStocks(String companyCode, Date startDate, Date endDate) {
		return null;
	}
}
