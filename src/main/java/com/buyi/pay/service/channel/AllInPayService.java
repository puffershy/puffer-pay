package com.buyi.pay.service.channel;

import org.springframework.stereotype.Component;

import com.buyi.pay.common.enums.ChannelType;

/**
 * 通联支付服务
 *
 * @author buyi
 * @date 2018年5月14日下午10:01:05
 * @since 1.0.0
 */
@Component
public class AllInPayService extends ChannelComposite {

	@Override
	public ChannelType getChannelType() {
		return ChannelType.ALL_IN_PAY;
	}

}
