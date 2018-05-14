package com.buyi.pay.vo.support;

import com.buyi.pay.common.enums.ChannelType;

public class PayoutReq {
	private ChannelType channelType;

	public ChannelType getChannelType() {
		return channelType;
	}

	public void setChannelType(ChannelType channelType) {
		this.channelType = channelType;
	}

}
