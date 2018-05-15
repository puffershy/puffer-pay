package com.buyi.pay.service.channel;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import com.buyi.common.log.Log;
import com.buyi.common.util.http.HttpResponse;
import com.buyi.common.util.http.HttpUtil;
import com.buyi.common.util.lang.time.DateUtil;
import com.buyi.common.util.xml.XMLUtil;
import com.buyi.pay.common.enums.AllInPayRspCodeEnum;
import com.buyi.pay.common.enums.ChannelType;
import com.buyi.pay.common.util.AllInPayUtil;
import com.buyi.pay.exception.PayExceptionCode;
import com.buyi.pay.vo.channel.allinpay.AllInPayConfig;
import com.buyi.pay.vo.channel.allinpay.AllInPayReqXML;
import com.buyi.pay.vo.channel.allinpay.AllInPayRspBody;
import com.buyi.pay.vo.channel.allinpay.AllInPayRspXML;
import com.buyi.pay.vo.channel.allinpay.AllInPayRspXML.Info;
import com.buyi.pay.vo.channel.allinpay.PayoutReqTransXML;
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

	/** 默认请求协议 */
	private static final String DEFAULT_CONTENT_TYPE = "application/tlt-notify";

	private static final String DEFAULT_CHARSET = "GBK";

	@Override
	public ChannelType getChannelType() {
		return ChannelType.ALL_IN_PAY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AllInPayConfig getChannelConfig(String merchantCode) {
		return super.getConfig(merchantCode, AllInPayConfig.class);
	}

	@Override
	public void payoutHandler(PayoutReq req, DataBinder binder) throws Exception {
		final String op = "AllInPayService.payoutHandler";
		AllInPayConfig config = getChannelConfig(req.getMerchantCode());

		// 格式化时间，通联时间格式YYYYMMDDHHMMSS
		String submitTime = DateUtil.formatDate(req.getOrderTime(), DateUtil.FORMAT_YYYYMMDDHHMMSS);

		// 业务代码，默认走代付-“09900”
		PayoutReqTransXML transXML = PayoutReqTransXML.newInstance().businessCode("09900")
				.merchantId(config.getMerchantId()).submitTime(submitTime).userCode(req.getUserId())
				.accountNo(req.getInCardNo()).accountName(req.getInName()).accountProp("0")
				.amount(String.valueOf(req.getAmount()));

		AllInPayReqXML allInPayReqXML = AllInPayReqXML.Info.newInstance().trxCode(config.getTrxCode())
				.version(config.getVersion()).dataType(config.getDataType()).level(config.getLevel())
				.userName(config.getUserName()).userPass(config.getPassword()).reqSn(req.getBusiId()).signedMsg("")
				.build().addBody(transXML);

		// 增加签名
		String xml = AllInPayUtil.sign(req.getMerchantCode(), allInPayReqXML, config.getPrivateKeyPath(),
				config.getPassword());

		binder.setReqStr(xml);
		binder.setUrl(config.getUrl());
		binder.setReqDate(new Date());

		log.info(Log.newInstance(op, "通联 | 单笔代付 | 请求报文").kv("busiId", req.getBusiId()).kv("url", config.getUrl())
				.kv("req", xml).toString());

		HttpResponse httpResponse = HttpUtil.post(config.getUrl(), xml, DEFAULT_CONTENT_TYPE, DEFAULT_CHARSET,
				config.getConnectTimeout(), config.getReadTimeout());

		String rspStr = httpResponse.getContent();

		binder.setRspStr(rspStr);

		log.info(Log.newInstance(op, "通联 | 单笔代付 | 响应报文").kv("busiId", req.getBusiId()).kv("url", config.getUrl())
				.kv("rsp", rspStr).toString());

		if (StringUtils.isBlank(rspStr)) {
			log.warn(Log.newInstance(op, "通联 | 单笔代付 | 响应报文为空").kv("busiId", req.getBusiId()).toString());
			throw PayExceptionCode.WARN_UNKNOWN.exp();
		}

		boolean sign = AllInPayUtil.verfiySign(req.getMerchantCode(), rspStr, config.getPublicKeyPath());

		if (!sign) {
			// 响应报文验签不通过
			log.error(Log.newInstance(op, "通联 | 单笔代付 | 请求响应报文验签不通过，订单更新成失败，需要人工介入").kv("busiId", req.getBusiId())
					.toString());

			// 第三方公司验签不通过，返回未知状态
			throw PayExceptionCode.WARN_UNKNOWN.exp();
		}

		// step4.转换通联返回参数为支付返回参数

		AllInPayRspXML fromXMLForXStream = XMLUtil.fromXML(rspStr, AllInPayRspXML.class);
		Pair<String, String> pairRspCode = transformRspCode(fromXMLForXStream);

		binder.setResult(pairRspCode.getLeft(), pairRspCode.getRight());

	}

	private Pair<String, String> transformRspCode(AllInPayRspXML rspXML) {
		final String op = "AllInPayFacade.transformRspCode";
		if (rspXML == null || rspXML.getInfo() == null) {
			log.error(Log.newInstance(op, "转换通联响应码，响应参数实体为空，响应业务状态码为未知状态，需要人工介入").kv("AllInPayRspXML", rspXML)
					.toString());
			return Pair.of(PayExceptionCode.WARN_UNKNOWN.getCode(), "未知状态，响应信息为空");
		}

		Info info = rspXML.getInfo();

		String infoRetCode = info.getReqCode();
		String infoErrMsg = info.getErrMsg();
		String errMsg = infoErrMsg;
		if (AllInPayRspCodeEnum.isDealing(infoRetCode)) {
			// 如果info响应码为空处理中
			return Pair.of(PayExceptionCode.WARN_DEALING.getCode(),
					StringUtils.isBlank(infoErrMsg) ? PayExceptionCode.WARN_DEALING.getDesc() : infoErrMsg);
		}

		// 判断100014-批次号或序列号冲突，订单重复
		if (AllInPayRspCodeEnum.isDuplicate(infoRetCode)) {
			return Pair.of(PayExceptionCode.ERROR_DUPLICATE.getCode(),
					StringUtils.isBlank(infoErrMsg) ? PayExceptionCode.ERROR_DUPLICATE.getDesc() : infoErrMsg);
		}

		if (AllInPayRspCodeEnum.isSuccess(infoRetCode)) {
			// 如果info响应为成功，则继续判断transf的响应码

			// AllInPayRspTransXML transret = rspXML.getTransret();
			AllInPayRspBody body = (AllInPayRspBody) rspXML.getBody().get(0);
			if (body == null) {
				log.error(Log.newInstance(op, "转换通联响应码，响应参数实体为空，响应业务状态码为未知状态，需要人工介入").kv("AllInPayRspXML", body)
						.toString());
				return Pair.of(PayExceptionCode.WARN_UNKNOWN.getCode(), "未知状态，响应信息为空");
			}

			String transretRetCode = body.getRetCode();
			String transretErrMsg = body.getErrMsg();
			errMsg = transretErrMsg;
			if (AllInPayRspCodeEnum.isSuccess(transretRetCode)) {
				// 原响应码RspCodeEnum.SUCCESS没有描述，使用PayExceptionCode.SUCCESS
				return Pair.of(PayExceptionCode.SUCCESS.getCode(), PayExceptionCode.SUCCESS.getDesc());
			}

			// 如果是处理中状态，则返回待处理
			if (AllInPayRspCodeEnum.isDealing(transretRetCode)) {
				return Pair.of(PayExceptionCode.WARN_DEALING.getCode(),
						StringUtils.isBlank(transretErrMsg) ? PayExceptionCode.WARN_DEALING.getDesc() : transretErrMsg);
			}
		}

		// 如果info响应码不为成功，则默认为失败,或者trans响应码不为成功， 则默认为失败
		return Pair.of(PayExceptionCode.ERROR_FAIL.getCode(),
				StringUtils.isBlank(errMsg) ? PayExceptionCode.ERROR_FAIL.getDesc() : errMsg);
	}

}
