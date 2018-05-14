package com.buyi.pay.vo.support;

import com.buyi.pay.common.enums.ChannelType;

public class PayoutReq {
	private ChannelType channelType;

	public static PayoutReq newInstance() {
		return new PayoutReq();
	}

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

	public PayoutReq channelType(ChannelType channelType) {
		setChannelType(channelType);
		return this;
	}

}
