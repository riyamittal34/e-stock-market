package com.stock.market.dto;

public class CompanyDto {

	private String comapanyId;

	private String companyCode;

	private String companyName;

	public String getComapanyId() {
		return comapanyId;
	}

	public void setComapanyId(String comapanyId) {
		this.comapanyId = comapanyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CompanyDto [comapanyId=" + comapanyId + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ "]";
	}
}
