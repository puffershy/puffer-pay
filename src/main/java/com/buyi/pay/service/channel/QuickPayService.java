package com.buyi.pay.service.channel;

import org.springframework.stereotype.Component;

import com.buyi.pay.common.annotation.Channel;
import com.buyi.pay.common.enums.ChannelType;

/**
 * 快钱支付服务
 *
 * @author buyi
 * @date 2018年5月14日下午10:01:21
 * @since 1.0.0
 */
@Component
@Channel(ChannelType.QUICK_PAY)
public class QuickPayService extends ChannelComposite {

	@Override
	public ChannelType getChannelType() {
		return ChannelType.ALL_IN_PAY;
	}

}
