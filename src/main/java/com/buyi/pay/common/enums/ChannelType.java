package com.buyi.pay.common.enums;

/**
 * 通道类型
 *
 * @author buyi
 * @date 2018年5月14日下午10:04:35
 * @since 1.0.0
 */
public enum ChannelType {
	QUICK_PAY("1"), ALL_IN_PAY("2");

	private String value;

	private ChannelType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
