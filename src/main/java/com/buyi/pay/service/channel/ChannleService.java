package com.buyi.pay.service.channel;

import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutReq;

public interface ChannleService {

	public DataBinder payout(PayoutReq req);

}
