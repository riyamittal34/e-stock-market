package com.stock.market.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Class CompanyDao.
 */
@Document("Company")
public class CompanyDao {

	/** The company id. */
	@Id
	private String companyId;

	/** The company code. */
	@Indexed(unique = true)
	private String companyCode;

	/** The company name. */
	private String companyName;

	/** The company ceo. */
	private String companyCeo;

	/** The company turnover. */
	private String companyTurnover;

	/** The company website. */
	private String companyWebsite;

	/** The stock exchange. */
	private String stockExchange;

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param companyId the new company id
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * Gets the company code.
	 *
	 * @return the company code
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * Sets the company code.
	 *
	 * @param companyCode the new company code
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the company ceo.
	 *
	 * @return the company ceo
	 */
	public String getCompanyCeo() {
		return companyCeo;
	}

	/**
	 * Sets the company ceo.
	 *
	 * @param companyCeo the new company ceo
	 */
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}

	/**
	 * Gets the company turnover.
	 *
	 * @return the company turnover
	 */
	public String getCompanyTurnover() {
		return companyTurnover;
	}

	/**
	 * Sets the company turnover.
	 *
	 * @param companyTurnover the new company turnover
	 */
	public void setCompanyTurnover(String companyTurnover) {
		this.companyTurnover = companyTurnover;
	}

	/**
	 * Gets the company website.
	 *
	 * @return the company website
	 */
	public String getCompanyWebsite() {
		return companyWebsite;
	}

	/**
	 * Sets the company website.
	 *
	 * @param companyWebsite the new company website
	 */
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	/**
	 * Gets the stock exchange.
	 *
	 * @return the stock exchange
	 */
	public String getStockExchange() {
		return stockExchange;
	}

	/**
	 * Sets the stock exchange.
	 *
	 * @param stockExchange the new stock exchange
	 */
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CompanyDao [companyId=" + companyId + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", companyCeo=" + companyCeo + ", companyTurnover=" + companyTurnover + ", companyWebsite="
				+ companyWebsite + ", stockExchange=" + stockExchange + "]";
	}
}
