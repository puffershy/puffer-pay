package com.buyi.pay.vo.channel.allinpay;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 通联支付配置
 * 
 * @author buyi
 * @date 2017-12-26 11:06:13
 * @since v1.0.0
 */
@SuppressWarnings("serial")
public class AllInPayConfig implements Serializable {

	/** 公钥证书路径 */
	private String publicKeyPath;

	/** 私钥证书路径 */
	private String privateKeyPath;

	/** 商户号 */
	private String merchantId;

	/** 用户名 */
	private String userName;

	/** 用户密码 */
	private String password;

	/** 交易代码 */
	private String trxCode;

	/** 版本 */
	private String version;

	/** 数据格式 */
	private String dataType;

	/** 处理级别 */
	private String level;

	/** 连接超时，默认10秒 */
	private Integer connectTimeout = 10000;

	/** 读取超时，默认30秒 */
	private Integer readTimeout = 30000;

	/** 路径 */
	private String url;

	/** 查询交易代码 */
	private String queryTrxCode;

	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	public void setPublicKeyPath(String publicKeyPath) {
		this.publicKeyPath = publicKeyPath;
	}

	public String getPrivateKeyPath() {
		return privateKeyPath;
	}

	public void setPrivateKeyPath(String privateKeyPath) {
		this.privateKeyPath = privateKeyPath;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String getQueryTrxCode() {
		return queryTrxCode;
	}

	public void setQueryTrxCode(String queryTrxCode) {
		this.queryTrxCode = queryTrxCode;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
