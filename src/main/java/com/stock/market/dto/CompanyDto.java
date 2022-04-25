package com.stock.market.dto;

/**
 * The Class CompanyDto.
 */
public class CompanyDto {

	/** The comapany id. */
	private String comapanyId;
	
	/** The company code. */
	private String companyCode;
	
	/** The company name. */
	private String companyName;

	/**
	 * Gets the comapany id.
	 *
	 * @return the comapany id
	 */
	public String getComapanyId() {
		return comapanyId;
	}

	/**
	 * Sets the comapany id.
	 *
	 * @param comapanyId the new comapany id
	 */
	public void setComapanyId(String comapanyId) {
		this.comapanyId = comapanyId;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CompanyDto [comapanyId=" + comapanyId + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ "]";
	}
}
