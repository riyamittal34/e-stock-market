package com.stock.market.service;

import java.util.List;

import com.stock.market.entity.CompanyDao;

public interface CompanyService {

	public Boolean registerCompany(String requestBody) throws Exception;

	public CompanyDao getCompanyByCompanyCode(String companyCode) throws Exception;

	public List<CompanyDao> getAllCompanyDetails() throws Exception;

	public Boolean deleteCompanyByCompanyCode(String companyCode) throws Exception;
}
