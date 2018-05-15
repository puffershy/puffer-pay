package com.buyi.pay.service.channel;

import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutReq;

public interface ChannelHandler {

	public ChannelType getChannelType();

	public void payoutHandler(PayoutReq req, DataBinder binder);
}
