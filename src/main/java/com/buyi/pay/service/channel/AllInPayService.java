package com.buyi.pay.service.channel;

import org.springframework.stereotype.Component;

import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutReq;

import lombok.extern.slf4j.Slf4j;

/**
 * 通联支付服务
 *
 * @author buyi
 * @date 2018年5月14日下午10:01:05
 * @since 1.0.0
 */
@Slf4j
@Component
public class AllInPayService extends AbstractChannelHandler {

	@Override
	public ChannelType getChannelType() {
		return ChannelType.ALL_IN_PAY;
	}

	@Override
	public void payoutHandler(PayoutReq req, DataBinder binder) {
		log.info("开始执行代付{}", getChannelType().name());
	}

}
