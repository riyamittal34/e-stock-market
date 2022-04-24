package com.stock.market.service;

import java.util.List;

public interface CompanyService {

	public String registerCompany(String requestBody);

	public String getCompanyByCompanyCode(String companyCode);

	public List<String> getAllCompanyDetails();

	public String deleteCompanyByCompanyCode(String companyCode);
}
