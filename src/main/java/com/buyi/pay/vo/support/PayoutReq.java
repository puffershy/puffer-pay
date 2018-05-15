package com.buyi.pay.vo.support;

import java.util.Date;

import com.buyi.pay.common.enums.ChannelType;

/**
 * 代付请求参数
 * 
 * @author buyi
 * @since 1.0.0
 * @date 2018下午2:16:11
 */
public class PayoutReq {
	/** 支付通道 */
	private ChannelType channelType;

	/** 商户号 */
	private String merchantCode;

	/** 业务流水号 */
	private String busiId;

	/** 订单时间 */
	private Date orderTime;

	private String userId;

	/** 收款银行卡 */
	private String inCardNo;

	/** 收款姓名 */
	private String inName;

	/** 收款金额 */
	private long amount;

	public static PayoutReq newInstance() {
		return new PayoutReq();
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInCardNo() {
		return inCardNo;
	}

	public void setInCardNo(String inCardNo) {
		this.inCardNo = inCardNo;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public PayoutReq channelType(ChannelType channelType) {
		setChannelType(channelType);
		return this;
	}

}
