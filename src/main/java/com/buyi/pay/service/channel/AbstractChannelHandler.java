package com.buyi.pay.service.channel;

import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.exception.PayExceptionCode;
import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutReq;


public abstract class AbstractChannelHandler implements ChannelHandler {
	/**
	 * 获取渠道类型
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午11:12:28
	 * @since 1.0.0
	 * @return
	 */
	public abstract ChannelType getChannelType();

	@Override
	public void payoutHandler(PayoutReq req, DataBinder binder) {
		throw PayExceptionCode.ERROR_NOT_SUPPORT_CHANNEL.exp();
	}

}
