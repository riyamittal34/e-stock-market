package com.stock.market.dto;

/**
 * The Class CompanyDtoBuilder.
 */
public class CompanyDtoBuilder {

	/** The comapany id. */
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

	/**
	 * Sets the comapany id.
	 *
	 * @param companyId the comapany id
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setCompanyId(String companyId) {
		this.companyId = companyId;
		return this;
	}

	/**
	 * Sets the company code.
	 *
	 * @param companyCode the company code
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
		return this;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the company name
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	/**
	 * Sets the company ceo.
	 *
	 * @param companyCeo the company ceo
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
		return this;
	}

	/**
	 * Sets the company turnover.
	 *
	 * @param companyTurnover the company turnover
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setCompanyTurnover(String companyTurnover) {
		this.companyTurnover = companyTurnover;
		return this;
	}

	/**
	 * Sets the company website.
	 *
	 * @param companyWebsite the company website
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
		return this;
	}

	/**
	 * Sets the stock exchange.
	 *
	 * @param stockExchange the stock exchange
	 * @return the company dto builder
	 */
	public CompanyDtoBuilder setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
		return this;
	}
	
	/**
	 * Builds the.
	 *
	 * @return the company dto
	 */
	public CompanyDto build() {
		CompanyDto company = new CompanyDto();
		company.setCompanyId(this.companyId);
		company.setCompanyCeo(this.companyCeo);
		company.setCompanyCode(this.companyCode);
		company.setCompanyName(this.companyName);
		company.setCompanyTurnover(this.companyTurnover);
		company.setCompanyWebsite(this.companyWebsite);
		company.setStockExchange(this.stockExchange);
		return company;
	}

}
