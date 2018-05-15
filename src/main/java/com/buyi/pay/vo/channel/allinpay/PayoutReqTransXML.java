package com.buyi.pay.vo.channel.allinpay;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 通联代付请求参数
 * 
 * @author buyi
 * @date 2017-12-26 10:31:33
 * @since v1.0.0
 */
@XStreamAlias("TRANS")
public class PayoutReqTransXML {
	/** 业务代码 */
	@NotBlank(message = "businessCode不能为空")
	@XStreamAlias("BUSINESS_CODE")
	private String businessCode;

	/** 商户代码 */
	@NotBlank(message = "merchantId不能为空")
	@XStreamAlias("MERCHANT_ID")
	private String merchantId;

	/** 提交时间，YYYYMMDDHHMMSS */
	@NotBlank(message = "submitTime不能为空")
	@XStreamAlias("SUBMIT_TIME")
	private String submitTime;

	/** 用户编号 */
	@NotBlank(message = "userCode不能为空")
	@XStreamAlias("E_USER_CODE")
	private String userCode;

	/** 有效期 */
	@XStreamAlias("VALIDATE")
	private String validate;

	/** 信用卡CVV2 */
	@XStreamAlias("CVV2")
	private String cvv2;

	/** 银行代码,可以不填，根据通联卡bin查询 */
	@XStreamAlias("BANK_CODE")
	private String bankCode;

	/** 开户行名称 */
	@XStreamAlias("BANK_NAME")
	private String bankName;

	/** 账号类型 */
	@XStreamAlias("ACCOUNT_TYPE")
	private String accountType;

	/** 账号 */
	@NotBlank(message = "accountNo不能为空")
	@XStreamAlias("ACCOUNT_NO")
	private String accountNo;

	/** 账号名 */
	@XStreamAlias("ACCOUNT_NAME")
	private String accountName;

	/** 开户行所在省 */
	@XStreamAlias("PROVINCE")
	private String province;

	/** 开户行所在市 */
	@XStreamAlias("CITY")
	private String city;

	/** 账号属性 */
	@NotBlank(message = "accountProp不能为空")
	@XStreamAlias("ACCOUNT_PROP")
	private String accountProp;
	/** 金额 */
	@NotBlank(message = "amount不能为空")
	@XStreamAlias("AMOUNT")
	private String amount;

	/** 货币类型，如果不填，通联默认人民币 */
	@XStreamAlias("CURRENCY")
	private String currency;

	/** 开户证件类型 */
	@XStreamAlias("ID_TYPE")
	private String idType;

	/** 证件号 */
	@XStreamAlias("ID")
	private String id;

	/** 本交易结算户 */
	@XStreamAlias("SETTACCT")
	private String settacct;

	/** 手机号 */
	@XStreamAlias("TEL")
	private String tel;

	/** 商户自定义的用户号，开发人员可当作备注字段使用 */
	@XStreamAlias("CUST_USERID")
	private String custUserId;

	/** 分组清算标志 */
	@XStreamAlias("SETTGROUPFLAG")
	private String settgroupFlag;

	/** 支付行号 */
	@XStreamAlias("UNION_BANK")
	private String unionBank;

	/** 支付附言 */
	@XStreamAlias("SUMMARY")
	private String summary;

	/** 备注 */
	@XStreamAlias("REMARK")
	private String remark;

	public static PayoutReqTransXML newInstance() {
		return new PayoutReqTransXML();
	}

	public PayoutReqTransXML businessCode(String businessCode) {
		this.businessCode = businessCode;
		return this;
	}

	public PayoutReqTransXML merchantId(String merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public PayoutReqTransXML submitTime(String submitTime) {
		this.submitTime = submitTime;
		return this;
	}

	public PayoutReqTransXML userCode(String userCode) {
		this.userCode = userCode;
		return this;
	}

	public PayoutReqTransXML accountNo(String accountNo) {
		this.accountNo = accountNo;
		return this;
	}

	public PayoutReqTransXML accountName(String accountName) {
		this.accountName = accountName;
		return this;
	}

	public PayoutReqTransXML accountProp(String accountProp) {
		this.accountProp = accountProp;
		return this;
	}

	public PayoutReqTransXML amount(String amount) {
		this.amount = amount;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
