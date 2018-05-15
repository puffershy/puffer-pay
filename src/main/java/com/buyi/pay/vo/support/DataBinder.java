package com.buyi.pay.vo.support;

import java.util.Date;

import com.buyi.pay.exception.BusiException;
import com.buyi.pay.exception.PayExceptionCode;

/**
 * 数据绑定抽象类
 * 
 * @author buyi
 * @date 2018年5月14日下午10:14:36
 * @since 1.0.0
 */
public abstract class DataBinder {

	/**
	 * 异常
	 */
	private Exception exception;

	/**
	 * 请求路径
	 */
	private String url;
	/**
	 * 请求报文
	 */
	private String reqStr;
	/**
	 * 响应报文
	 */
	private String rspStr;
	/**
	 * 请求时间
	 */
	private Date reqDate;

	/**
	 * 获取响应码
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午10:19:35
	 * @since 1.0.0
	 * @return
	 */
	public abstract String getRspCode();

	public abstract void setRspCode(String code);

	/**
	 * 获取响应描述信息
	 *
	 * @author buyi
	 * @date 2018年5月14日下午10:20:02
	 * @since 1.0.0
	 * @return
	 */
	public abstract String getRspMsg();

	public abstract void setRspMsg(String msg);

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReqStr() {
		return reqStr;
	}

	public void setReqStr(String reqStr) {
		this.reqStr = reqStr;
	}

	public String getRspStr() {
		return rspStr;
	}

	public void setRspStr(String rspStr) {
		this.rspStr = rspStr;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	/**
	 * 判断是否异常
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午11:47:15
	 * @since 1.0.0
	 * @return 如果异常，则返回true;否则，返回false;
	 */
	public boolean isError() {
		return exception != null;
	}

	public void setResult(String code, String msg) {
		setRspCode(code);
		setRspMsg(msg);
	}

	public void setResult(PayExceptionCode code) {
		setRspCode(code.getCode());
		setRspMsg(code.getDesc());
	}
}
