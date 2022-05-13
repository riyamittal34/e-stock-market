package com.stock.market;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 * The Class StockMarketApplication.
 */
@SpringBootApplication
@EnableMongoRepositories
@EnableEurekaClient
@OpenAPIDefinition
public class StockMarketApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(StockMarketApplication.class, args);
	}
	
	 @Bean
	  public GroupedOpenApi api()
	  {
	      return GroupedOpenApi.builder()
	              .group("CompanyController")
	              .packagesToScan("com.stock.market")
	              .build();
	  }

}
