package com.buyi.pay.service.channel;

import java.util.Map;

import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.exception.PayExceptionCode;
import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutDataBinder;
import com.buyi.pay.vo.support.PayoutReq;
import com.google.common.collect.Maps;

/**
 * 支付渠道适配
 *
 * @author buyi
 * @date 2018年5月14日下午9:58:39
 * @since 1.0.0
 */
public abstract class ChannelComposite implements ChannleService {
	private final Map<ChannelType, ChannelComposite> adapter = Maps.newConcurrentMap();

	/**
	 * 获取渠道类型
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午11:12:28
	 * @since 1.0.0
	 * @return
	 */
	public abstract ChannelType getChannelType();

	public ChannelComposite() {
		setAdapter(getChannelType(), this);
	}

	public void setAdapter(ChannelType channelType, ChannelComposite service) {
		if (adapter.get(channelType) == null) {
			adapter.put(channelType, service);
		}
	}

	/**
	 * 根据类型获取相应支付通道
	 *
	 * @author buyi
	 * @date 2018年5月14日下午11:14:38
	 * @since 1.0.0
	 * @param channelType
	 * @return
	 */
	private ChannelComposite getChannleService(ChannelType channelType) {
		return adapter.get(channelType);
	}

	@Override
	public DataBinder payout(PayoutReq req) {
		DataBinder binder = new PayoutDataBinder();
		ChannelComposite channleService = getChannleService(req.getChannelType());

		channleService.payoutHandler(req, binder);

		return binder;
	}

	/**
	 * 处理参数
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午11:22:10
	 * @since 1.0.0
	 * @param req
	 * @param binder
	 */
	public void payoutHandler(PayoutReq req, DataBinder binder) {
		throw PayExceptionCode.ERROR_NOT_SUPPORT_CHANNEL.exp();
	}

}
