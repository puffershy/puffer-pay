package com.buyi.pay.vo.channel.allinpay;

/**
 * 通联响应接口
 * 
 * @author buyi
 * @date 2018-01-09 11:04:11
 * @since v1.0.0
 */
public interface AllInPayRspBody {

	/**
	 * 返回响应码
	 * 
	 * @author buyi
	 * @date 2018-01-09 11:04:25
	 * @since v1.0.0
	 *
	 * @return
	 */
	public String getRetCode();

	/**
	 * 返回响应信息
	 * 
	 * @author buyi
	 * @date 2018-01-09 11:04:33
	 * @since v1.0.0
	 *
	 * @return
	 */
	public String getErrMsg();

}
