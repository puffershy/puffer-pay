package com.buyi.pay.vo.channel.allinpay;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 通联交易结果查询请求响应参数
 * <p>
 * 通联查询是批量查询，我们只实现单记录的查询
 * 
 * @author buyi
 * @date 2017-12-26 10:31:33
 * @since v1.0.0
 */
@XStreamAlias("QTRANSRSP")
public class QueryRspXML implements AllInPayRspBody {
	private static final Logger logger = LoggerFactory.getLogger(QueryRspXML.class);

	@XStreamImplicit(itemFieldName = "QTDETAIL")
	private List<QueryRspDetail> list;

	public List<QueryRspDetail> getList() {
		return list;
	}

	public void setList(List<QueryRspDetail> list) {
		this.list = list;
	}

	@Override
	public String getRetCode() {
		final String op = "QueryRspXML.getRetCode";
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		return list.get(0).getRetCode();
	}

	@Override
	public String getErrMsg() {
		final String op = "QueryRspXML.getErrMsg";
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		return list.get(0).getErrMsg();
	}

	public static class QueryRspDetail {
		@XStreamAlias("BATCHID")
		private String batchId;
		@XStreamAlias("SN")
		private String sn;
		@XStreamAlias("TRXDIR")
		private String trxDir;
		@XStreamAlias("SETTDAY")
		private String settDay;
		@XStreamAlias("FINTIME")
		private String finTime;
		@XStreamAlias("SUBMITTIME")
		private String submitTime;
		@XStreamAlias("ACCOUNT_NO")
		private String accountNo;
		@XStreamAlias("ACCOUNT_NAME")
		private String accountName;
		@XStreamAlias("AMOUNT")
		private String amount;
		@XStreamAlias("CUST_USERID")
		private String custUserId;
		@XStreamAlias("REMARK")
		private String remark;
		@XStreamAlias("SUMMARY")
		private String summary;
		@XStreamAlias("RET_CODE")
		private String retCode;
		@XStreamAlias("ERR_MSG")
		private String errMsg;

		public String getBatchId() {
			return batchId;
		}

		public void setBatchId(String batchId) {
			this.batchId = batchId;
		}

		public String getSn() {
			return sn;
		}

		public void setSn(String sn) {
			this.sn = sn;
		}

		public String getTrxDir() {
			return trxDir;
		}

		public void setTrxDir(String trxDir) {
			this.trxDir = trxDir;
		}

		public String getSettDay() {
			return settDay;
		}

		public void setSettDay(String settDay) {
			this.settDay = settDay;
		}

		public String getFinTime() {
			return finTime;
		}

		public void setFinTime(String finTime) {
			this.finTime = finTime;
		}

		public String getSubmitTime() {
			return submitTime;
		}

		public void setSubmitTime(String submitTime) {
			this.submitTime = submitTime;
		}

		public String getAccountNo() {
			return accountNo;
		}

		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}

		public String getAccountName() {
			return accountName;
		}

		public void setAccountName(String accountName) {
			this.accountName = accountName;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getCustUserId() {
			return custUserId;
		}

		public void setCustUserId(String custUserId) {
			this.custUserId = custUserId;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getRetCode() {
			return retCode;
		}

		public void setRetCode(String retCode) {
			this.retCode = retCode;
		}

		public String getErrMsg() {
			return errMsg;
		}

		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
