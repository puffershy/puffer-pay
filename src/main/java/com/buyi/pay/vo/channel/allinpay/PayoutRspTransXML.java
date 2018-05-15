package com.buyi.pay.vo.channel.allinpay;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 通联代付请求响应参数
 * 
 * @author buyi
 * @date 2017-12-26 10:31:33
 * @since v1.0.0
 */
@XStreamAlias("TRANSRET")
public class PayoutRspTransXML implements AllInPayRspBody {
	/** 商户代码 */
	@XStreamAlias("MERCHANT_ID")
	private String merchantId;
	/** 返回码 */
	@XStreamAlias("RET_CODE")
	private String retCode;
	/** 错误文本 */
	@XStreamAlias("ERR_MSG")
	private String errMsg;

	/** 清算日期 */
	@XStreamAlias("SETTLE_DAY")
	private String settleDay;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	@Override
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getSettleDay() {
		return settleDay;
	}

	public void setSettleDay(String settleDay) {
		this.settleDay = settleDay;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
