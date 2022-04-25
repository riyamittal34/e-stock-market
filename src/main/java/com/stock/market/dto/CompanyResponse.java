package com.stock.market.dto;

/**
 * The Class CompanyResponse.
 *
 * @param <D> the generic type
 */
public class CompanyResponse<D> {

	/** The message. */
	private ResponseMessage message;
	
	/** The data. */
	private D data;

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public ResponseMessage getMessage() {
		return this.message;
	}

	/**
	 * With message.
	 *
	 * @param message the message
	 * @return the company response
	 */
	public CompanyResponse<D> withMessage(ResponseMessage message) {
		this.message = message;
		return this;
	}

	/**
	 * With data.
	 *
	 * @param data the data
	 * @return the company response
	 */
	public CompanyResponse<D> withData(D data) {
		this.data = data;
		return this;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public D getData() {
		return data;
	}
}
