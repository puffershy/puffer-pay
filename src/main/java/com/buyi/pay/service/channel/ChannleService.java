package com.buyi.pay.service.channel;

import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutReq;

/**
 * 支付通道服务
 *
 * @author buyi
 * @date 2018年5月14日下午11:43:13
 * @since 1.0.0
 */
public interface ChannleService {

	/**
	 * 发起代付请求
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午11:42:52
	 * @since 1.0.0
	 * @param req
	 * @return
	 */
	public DataBinder payout(PayoutReq req);

}
