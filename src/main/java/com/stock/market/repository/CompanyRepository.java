package com.stock.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.entity.CompanyDao;

/**
 * The Interface CompanyRepository.
 */
@Repository
public interface CompanyRepository extends MongoRepository<CompanyDao, String> {

	/**
	 * Find by company code.
	 *
	 * @param companyCode the company code
	 * @return the company dao
	 */
	public CompanyDao findByCompanyCode(String companyCode);
}
