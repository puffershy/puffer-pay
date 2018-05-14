package com.buyi.pay.exception;

public class BusiException extends RuntimeException {
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
