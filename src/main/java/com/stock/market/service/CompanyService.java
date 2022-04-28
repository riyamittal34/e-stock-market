package com.stock.market.service;

import java.util.List;

import com.stock.market.entity.CompanyDao;

/**
 * The Interface CompanyService.
 */
public interface CompanyService {

	
	/**
	 * Register company.
	 *
	 * @param requestBody the request body
	 * @return the integer
	 * @throws Exception the exception
	 */
	public Integer registerCompany(String requestBody) throws Exception;

	/**
	 * Gets the company by company code.
	 *
	 * @param companyCode the company code
	 * @return the company by company code
	 * @throws Exception the exception
	 */
	public CompanyDao getCompanyByCompanyCode(String companyCode) throws Exception;

	/**
	 * Gets the all company details.
	 *
	 * @return the all company details
	 * @throws Exception the exception
	 */
	public List<CompanyDao> getAllCompanyDetails() throws Exception;

	/**
	 * Delete company by company code.
	 *
	 * @param companyCode the company code
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean deleteCompanyByCompanyCode(String companyCode) throws Exception;
}
