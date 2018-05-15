package com.buyi.pay.vo.channel.allinpay;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.buyi.common.util.xml.XMLUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 通联请求参数模板类
 * 
 * @author buyi
 * @date 2017-12-26 10:17:18
 * @since v1.0.0
 */
@XStreamAlias("AIPG")
public class AllInPayReqXML {

	@Transient
	private static final String ALLINPAY_XML_PREFIX = "<?xml version=\"1.0\" encoding=\"GBK\"?>";

	@NotNull(message = "info不能为空")
	@XStreamAlias("INFO")
	private Info info;

	// @XStreamAlias("TRANS")
	@NotNull(message = "body不能为空")
	@XStreamImplicit
	private List<Object> list;

	public AllInPayReqXML(Info info) {
		this.info = info;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public AllInPayReqXML addBody(Object body) {
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(body);
		return this;
	}

	public static final class Info {
		/** 交易代码 */
		@NotBlank(message = "trxCode不能为空")
		@XStreamAlias("TRX_CODE")
		private String trxCode;
		/** 版本 */
		@NotBlank(message = "version不能为空")
		@XStreamAlias("VERSION")
		private String version;
		/** 数据格式 */
		@NotBlank(message = "dataType不能为空")
		@XStreamAlias("DATA_TYPE")
		private String dataType;
		/** 处理级别 */
		@NotBlank(message = "不能为空")
		@XStreamAlias("LEVEL")
		private String level;
		/** 用户名 */
		@NotBlank(message = "userName不能为空")
		@XStreamAlias("USER_NAME")
		private String userName;
		/** 用户密码 */
		@NotBlank(message = "userPass不能为空")
		@XStreamAlias("USER_PASS")
		private String userPass;
		/** 交易批次号 */
		@NotBlank(message = "reqSn不能为空")
		@XStreamAlias("REQ_SN")
		private String reqSn;
		/** 签名信息 */
		@NotBlank(message = "signedMsg不能为空")
		@XStreamAlias("SIGNED_MSG")
		private String signedMsg = "";

		/**
		 * @author buyi
		 * @date 2017-12-26 11:15:32
		 * @since v1.0.0
		 * @return
		 */
		public static Info newInstance() {
			return new Info();
		}

		public Info trxCode(String trxCode) {
			this.trxCode = trxCode;
			return this;
		}

		public Info version(String version) {
			this.version = version;
			return this;
		}

		public Info dataType(String dataType) {
			this.dataType = dataType;
			return this;
		}

		public Info level(String level) {
			this.level = level;
			return this;
		}

		public Info userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Info userPass(String userPass) {
			this.userPass = userPass;
			return this;
		}

		public Info reqSn(String reqSn) {
			this.reqSn = reqSn;
			return this;
		}

		public Info signedMsg(String signedMsg) {
			this.signedMsg = signedMsg;
			return this;
		}

		public AllInPayReqXML build() {
			return new AllInPayReqXML(this);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	/**
	 * 构建xml字符串
	 * 
	 * @author buyi
	 * @date 2017-12-26 10:36:10
	 * @since v1.0.0
	 * @return
	 */
	public String buildXML() {
		String xml = XMLUtil.toXMLWithoutDeclare(this);
		return ALLINPAY_XML_PREFIX.concat(xml);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
