package com.buyi.pay.exception;

/**
 *
 * @author buyi
 * @date 2018年5月14日下午10:28:48
 * @since 1.0.0
 */
public enum PayExceptionCode {
	SUCCESS("0000"),

	WARN_TIMEOUT("1001"),

	WARN_DEALING("1002"),

	/** 不支持的通道 */
	ERROR_NOT_SUPPORT_CHANNEL("2001"),

	/** 网络异常 */
	ERROR_NET("9999");

	private String value;

	private PayExceptionCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
