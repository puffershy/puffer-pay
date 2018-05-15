package com.buyi.pay.vo.channel.allinpay;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 通联响应参数模板类
 * 
 * @author buyi
 * @date 2017-12-26 10:17:18
 * @since v1.0.0
 */
@XStreamAlias("AIPG")
public class AllInPayRspXML {

	@XStreamAlias("INFO")
	private Info info;

	@XStreamImplicit
	private List<Object> body;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<Object> getBody() {
		return body;
	}

	public void setBody(List<Object> body) {
		this.body = body;
	}

	public static final class Info {
		/** 交易代码 */
		@XStreamAlias("TRX_CODE")
		private String trxCode;
		/** 版本 */
		@XStreamAlias("VERSION")
		private String version;
		/** 数据格式 */
		@XStreamAlias("DATA_TYPE")
		private String dataType;
		/** 交易流水号 */
		@XStreamAlias("REQ_SN")
		private String reqSn;

		/** 返回代码 */
		@XStreamAlias("RET_CODE")
		private String reqCode;

		/** 错误信息 */
		@XStreamAlias("ERR_MSG")
		private String errMsg;

		/** 签名信息 */
		@XStreamAlias("SIGNED_MSG")
		private String signedMsg;

		public String getTrxCode() {
			return trxCode;
		}

		public void setTrxCode(String trxCode) {
			this.trxCode = trxCode;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

		public String getReqSn() {
			return reqSn;
		}

		public void setReqSn(String reqSn) {
			this.reqSn = reqSn;
		}

		public String getReqCode() {
			return reqCode;
		}

		public void setReqCode(String reqCode) {
			this.reqCode = reqCode;
		}

		public String getErrMsg() {
			return errMsg;
		}

		public void setErrMsg(String errMsg) {
			this.errMsg = errMsg;
		}

		public String getSignedMsg() {
			return signedMsg;
		}

		public void setSignedMsg(String signedMsg) {
			this.signedMsg = signedMsg;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
