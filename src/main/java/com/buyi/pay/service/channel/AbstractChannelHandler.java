package com.buyi.pay.service.channel;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.buyi.common.log.Log;
import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.exception.PayExceptionCode;
import com.buyi.pay.vo.support.DataBinder;
import com.buyi.pay.vo.support.PayoutReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractChannelHandler implements ChannelHandler {
	/**
	 * 获取渠道类型
	 * 
	 * @author buyi
	 * @date 2018年5月14日下午11:12:28
	 * @since 1.0.0
	 * @return
	 */
	public abstract ChannelType getChannelType();

	public abstract <T> T getChannelConfig(String merchantCode);

	public <T> T getConfig(String merchantCode, Class<T> clazz) {
		// TODO 获取支付通道配置信息
		String channelConfig = "";

		if (StringUtils.isBlank(channelConfig)) {
			log.warn(Log.newInstance("AbstractChannelHandler.getConfig", "缺少相关支付通道配置").kv("merchantCode", merchantCode)
					.kv("channelType", getChannelType()).toString());
			throw PayExceptionCode.ERROR_ILL_PARAM.exp();
		}

		// 转换成实体对象
		return JSON.parseObject(channelConfig, clazz);
	}

	@Override
	public void payoutHandler(PayoutReq req, DataBinder binder) throws Exception {
		throw PayExceptionCode.ERROR_NOT_SUPPORT_CHANNEL.exp();
	}

}
