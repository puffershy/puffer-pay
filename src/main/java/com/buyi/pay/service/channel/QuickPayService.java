package com.buyi.pay.service.channel;

import org.springframework.stereotype.Component;

import com.buyi.pay.common.enums.ChannelType;

/**
 * 快钱支付服务
 *
 * @author buyi
 * @date 2018年5月14日下午10:01:21
 * @since 1.0.0
 */
@Component
public class QuickPayService extends AbstractChannelHandler {

	@Override
	public ChannelType getChannelType() {
		return ChannelType.QUICK_PAY;
	}

	@Override
	public <T> T getChannelConfig(String merchantCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
