package com.buyi.pay.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 通联响应码枚举
 * 
 * @author buyi
 * @date 2018-01-04 14:30:03
 * @since v1.0.0
 */
public enum AllInPayRspCodeEnum {
	/** 成功 */
	SUCCESS("0000"),

	/************************************** 中间态响应码 begin */
	/** 系统正在对数据处理 中间状态 */
	SYSTEM_DEALING("2000"),
	/** 等待商户审核中间状态 */
	WAIT_MERCH_CHECK("2001"),
	/** 等待 受理中间状态 */
	WAIT_ACCEPT("2003"),
	/** 等待 复核中间状态 */
	WAIT_RE_CHECK("2005"),
	/** 提交银行处理中间状态 */
	SUBMIT_BANK_DEALING("2007"),
	/** 实时交易超时(中间状态,需要查询) */
	TRADE_TIMEOUT("2008"),

	/** 跨行交易 */
	INTER_BANK("4000"),
	/************************************** 中间态响应码 end */

	/**************************************** 交易失败响应码 begin */
	/** 报文内容检查错或者处理错 */
	POST_MSG_CHECK_ERR("1000"),
	/** 报文解释错 */
	POST_MSG_EXPLAIN_ERR("1001"),
	/** 冲正时无此交易 */
	REVERSE_ORDER_NOT_FOUND("1002"),
	/** 本批交易已经全部失败 */
	TRADE_ALL_FAIL("1999"),
	/** 系统处理失败表示最终失败 */
	SYSTEM_DEAL_ERR("0001"),
	/** 已撤销表示最终失败 */
	REPEALED("0002"),
	/** 商户审核不通过 最终失败 */
	MERCH_CHECK_FAIL("2002"),
	/** 不通过受理最终失败 */
	MERCH_ACCEPT_FAIL("2004"),
	/** 不通过复核最终失败 */
	MERCH_RE_CHECK_FAIL("2006"),
	/**************************************** 交易失败响应码 end */

	/** 批次号或序列号冲突 */
	SERIALNO_DUPLICAT("1108");

	private String value;

	private AllInPayRspCodeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public boolean isValue(String value) {
		return StringUtils.equals(getValue(), value);
	}

	/**
	 * 判断是否成功
	 * 
	 * @author buyi
	 * @date 2018-01-04 14:58:16
	 * @since v2.0.4
	 * @param value
	 * @return 如果成功则返回true；否则返回false
	 */
	public static boolean isSuccess(String value) {
		if (SUCCESS.isValue(value)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断响应码是否是处理中
	 * 
	 * @author buyi
	 * @date 2018-01-04 14:45:28
	 * @since v2.0.4
	 * @param code
	 * @return 如果是处理中则返回true;否则返回false
	 */
	public static boolean isDealing(String value) {
		if (SYSTEM_DEALING.isValue(value) || WAIT_MERCH_CHECK.isValue(value) || WAIT_ACCEPT.isValue(value)
				|| WAIT_RE_CHECK.isValue(value) || SUBMIT_BANK_DEALING.isValue(value) || TRADE_TIMEOUT.isValue(value)
				|| INTER_BANK.isValue(value)) {
			return true;
		}

		return false;
	}

	/**
	 * 判断是否失败
	 * 
	 * @author buyi
	 * @date 2018-01-04 15:00:44
	 * @since v2.0.4
	 * @param value
	 * @return 如果失败则返回true;否则返回false
	 */
	// public static boolean isFail(String value) {
	// if (POST_MSG_CHECK_ERR.isValue(value) ||
	// POST_MSG_EXPLAIN_ERR.isValue(value) ||
	// REVERSE_ORDER_NOT_FOUND.isValue(value)
	// || TRADE_ALL_FAIL.isValue(value) || SYSTEM_DEAL_ERR.isValue(value) ||
	// REPEALED.isValue(value) || MERCH_CHECK_FAIL.isValue(value)
	// || MERCH_ACCEPT_FAIL.isValue(value) ||
	// MERCH_RE_CHECK_FAIL.isValue(value)) {
	// return true;
	// }
	//
	// return false;
	// }

	/**
	 * 判断订单是否重复
	 * 
	 * @author buyi
	 * @date 2018-01-05 15:47:49
	 * @since v2.0.4
	 * @param value
	 * @return
	 */
	public static boolean isDuplicate(String value) {
		if (SERIALNO_DUPLICAT.isValue(value)) {
			return true;
		}

		return false;
	}

}
