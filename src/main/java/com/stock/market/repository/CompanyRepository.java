package com.stock.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.entity.CompanyDao;

@Repository
public interface CompanyRepository extends MongoRepository<CompanyDao, String> {

	public CompanyDao findByCompanyCode(String companyCode);
}
