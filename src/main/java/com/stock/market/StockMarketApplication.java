package com.stock.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The Class StockMarketApplication.
 */
@SpringBootApplication
@EnableMongoRepositories
public class StockMarketApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(StockMarketApplication.class, args);
	}

}
