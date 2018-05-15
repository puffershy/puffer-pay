package com.buyi.pay.service.channel;

import java.net.SocketTimeoutException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.exception.BusiException;
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
@Component
public class ChannelComposite implements ChannleService {
	private final Map<ChannelType, ChannelHandler> adapter = Maps.newConcurrentMap();

	@Resource
	private ApplicationContext applicationContext;

	/**
	 * 初始化参数
	 * 
	 * @author buyi
	 * @since 1.0.0
	 * @date 2018上午10:28:41
	 */
	@PostConstruct
	private void init() {
		Map<String, ChannelHandler> beansOfType = applicationContext.getBeansOfType(ChannelHandler.class);
		for (Object key : beansOfType.keySet()) {
			ChannelHandler channelHandler = beansOfType.get(key);
			ChannelType channelType = channelHandler.getChannelType();
			if (channelType == null) {
				throw new ExceptionInInitializerError("ChannelHandler.getChannelType() 缺少返回值");
			}

			if (adapter.get(channelType) != null) {
				throw new ExceptionInInitializerError("重复注入，" + channelHandler.getClass() + "已经存在的类型" + channelType);
			}

			adapter.put(channelType, channelHandler);
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
	private ChannelHandler getChannleService(ChannelType channelType) {
		return adapter.get(channelType);
	}

	@Override
	public DataBinder payout(PayoutReq req) {
		DataBinder binder = new PayoutDataBinder();
		try {
			if (req.getChannelType() == null) {
				throw PayExceptionCode.ERROR_ILL_PARAM.exp();
			}

			ChannelHandler channleService = getChannleService(req.getChannelType());

			channleService.payoutHandler(req, binder);
		} catch (SocketTimeoutException e) {
			binder.setException(e);
			binder.setResult(PayExceptionCode.WARN_TIMEOUT);
		} catch (BusiException e) {
			binder.setException(e);
			binder.setResult(e.getCode(), e.getMessage());
		} catch (Exception e) {
			binder.setException(e);
			binder.setResult(PayExceptionCode.ERROR_FAIL);
		}

		return binder;
	}
}
