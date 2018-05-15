package com.buyi.pay.vo.channel.allinpay;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 通联交易结果查询请求参数
 * 
 * @author buyi
 * @date 2017-12-26 10:31:33
 * @since v1.0.0
 */
@XStreamAlias("QTRANSREQ")
public class QueryReqXML {

	/** 商户代码 */
	@NotBlank(message = "merchantId不能为空")
	@XStreamAlias("MERCHANT_ID")
	private String merchantId;

	@NotBlank(message = "querySN不能为空")
	@XStreamAlias("QUERY_SN")
	private String querySN;

	/**
	 * 状态，默认就填2全部
	 */
	@NotBlank(message = "status不能为空")
	@XStreamAlias("STATUS")
	private String status;

	/**
	 * 查询类型，选择按提交日期1
	 */
	@NotBlank(message = "type不能为空")
	@XStreamAlias("TYPE")
	private String type;

	@XStreamAlias("START_DAY")
	private String startDay;

	@XStreamAlias("END_DAY")
	private String endDay;

	public static QueryReqXML newInstance() {
		return new QueryReqXML();
	}

	public QueryReqXML merchantId(String merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public QueryReqXML querySN(String querySN) {
		this.querySN = querySN;
		return this;
	}

	public QueryReqXML status(String status) {
		this.status = status;
		return this;
	}

	public QueryReqXML type(String type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
