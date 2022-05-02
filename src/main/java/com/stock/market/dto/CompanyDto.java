package com.stock.market.dto;

/**
 * The Class CompanyDto.
 */
public class CompanyDto {

	/** The companyId id. */
	private String companyId;

	/** The company code. */
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
	
	/** The latest stock price. */
	private Double latestStockPrice;

	/**
	 * Gets the comapany id.
	 *
	 * @return the comapany id
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the companyId id.
	 *
	 * @param companyId the new companyId id
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
	 * Gets the latest stock price.
	 *
	 * @return the latest stock price
	 */
	public Double getLatestStockPrice() {
		return latestStockPrice;
	}

	/**
	 * Sets the latest stock price.
	 *
	 * @param latestStockPrice the new latest stock price
	 */
	public void setLatestStockPrice(Double latestStockPrice) {
		this.latestStockPrice = latestStockPrice;
	}
}
