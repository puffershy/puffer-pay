package com.buyi.pay.exception;

/**
 *
 * @author buyi
 * @date 2018年5月14日下午10:28:48
 * @since 1.0.0
 */
public enum PayExceptionCode {
	SUCCESS("0000", "成功"),

	WARN_TIMEOUT("1001", "请求超时"),

	WARN_DEALING("1002", "处理中"),

	WARN_UNKNOWN("1999", "未知异常"),

	
	
	
	ERROR_NOT_SUPPORT_CHANNEL("2001", "不支持的通道"),

	ERROR_NOT_FOUND_ORDER("2002", "没有相应订单"),

	ERROR_ILL_PARAM("2003", "非法参数"),
	
	ERROR_DUPLICATE("2004","重复"),

	ERROR_FAIL("2999","失败"),
	/** 网络异常 */
	ERROR_NET("9999", "网络异常");

	private String code;
	private String desc;

	private PayExceptionCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public BusiException exp() {
		BusiException exception = new BusiException();
		exception.setCode(getCode());
		return exception;
	}

}
