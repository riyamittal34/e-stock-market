package com.stock.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stock.market.entity.UserDao;

@Repository
public interface UserRepository extends MongoRepository<UserDao, String> {

	UserDao findByUsername(String username);
}
