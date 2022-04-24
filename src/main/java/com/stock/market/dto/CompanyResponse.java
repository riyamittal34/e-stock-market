package com.stock.market.dto;

public class CompanyResponse<D> {

	private ResponseMessage message;
	private D data;

	public ResponseMessage getMessage() {
		return this.message;
	}

	public CompanyResponse<D> withMessage(ResponseMessage message) {
		this.message = message;
		return this;
	}

	public CompanyResponse<D> withData(D data) {
		this.data = data;
		return this;
	}

	public D getData() {
		return data;
	}
}
