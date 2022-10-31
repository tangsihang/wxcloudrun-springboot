package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.repository.AdsOrderRepository;
import com.tencent.wxcloudrun.service.WebhookService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Service
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    @Autowired
    private AdsOrderRepository adsOrderRepository;

    @Override
    public JSONObject respWxPayHook(JSONObject req) {
        log.info("微信下单-回调成功:{}", req.toJSONString());
        String outTradeNo = req.getString("outTradeNo");
        String openid = req.getString("openid");
        Integer cashFee = req.getInteger("cashFee");
        AdsOrderEntity orderEntity = adsOrderRepository.getOneByOrderNo(outTradeNo);
        if (orderEntity != null) {
            return respSuccess();
        }
        //TODO业务字段拓展
        AdsOrderEntity order = new AdsOrderEntity();
        order.setOpenid(openid);
        order.setOutTradeNo(outTradeNo);
        order.setFee(cashFee);
        order.setCallType("PAY");
        order.setResp(req.toJSONString());
        return respSuccess();
    }

    private JSONObject respSuccess() {
        JSONObject resp = new JSONObject();
        resp.put("errcode", 0);
        resp.put("errmsg", "OK");
        return resp;
    }
}
