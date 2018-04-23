package com.hanm.weixin.weixinpay.service;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.hanm.weixin.weixinpay.model.TdOrder;
import com.hanm.weixin.weixinpay.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.pay.PayJsRequest;
import weixin.popular.bean.paymch.MchOrderInfoResult;
import weixin.popular.bean.paymch.MchOrderquery;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;

/**
 * Created by 张钟 on 2017/9/27.
 */

@Service
public class WeiChatPayService {

    @Value("${wx.xcx.appId}")
    private String         appId;

    @Value("${wx.xcx.mchId}")
    private String         mchId;

    @Value("${wx.wcx.pay.key}")
    private String         wxPayKey;

    private String         tradeType = "JSAPI";

    @Autowired
    private WeiChatService weiChatService;

    /**
     * 统一下单
     * @param unifiedorder
     * @return
     */
    public UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder) {
        UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder, wxPayKey);
        return unifiedorderResult;
    }

    /**
     * 构建支付请求
     * @param openId
     * @return
     */
    public Unifiedorder buildUnifiedorder(String openId) {
        DecimalFormat df2 = new DecimalFormat("#.00");
        Date currentDate = new Date();
        String nonceStr = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String expire = DateUtils.getLongDateString(DateUtils.addMinutes(currentDate, 30));
        String start = DateUtils.getLongDateString(currentDate);
        String totalPrice = "1";
        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(appId);
        unifiedorder.setMch_id(mchId);
        unifiedorder.setNonce_str(nonceStr);
        unifiedorder.setBody("有赚养蟹");
        unifiedorder.setOut_trade_no(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        unifiedorder.setTotal_fee(totalPrice);
        unifiedorder.setNotify_url("https://95658398.qcloud.la/weiChat/anonymous/callBack.do");
        unifiedorder.setTrade_type(tradeType);
        unifiedorder.setOpenid(openId);
        unifiedorder.setAttach("有赚养蟹订单支付");
        unifiedorder.setTime_expire(expire);
        unifiedorder.setTime_start(start);
        unifiedorder.setGoods_tag("有赚养蟹-");
        return unifiedorder;
    }

    /**
     * 查询支付单状态
     * @param tdOrder
     * @return
     */
    public MchOrderInfoResult queryOrder(TdOrder tdOrder) {
        String nonceStr = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        MchOrderquery mchOrderquery = new MchOrderquery();
        mchOrderquery.setMch_id(mchId);
        mchOrderquery.setAppid(appId);
        mchOrderquery.setOut_trade_no(tdOrder.getId());
        mchOrderquery.setNonce_str(nonceStr);
        mchOrderquery.setSign_type("MD5");
        MchOrderInfoResult mchOrderInfoResult = PayMchAPI.payOrderquery(mchOrderquery,
            "8844cc2f98c1495b90fca7d4a2584674");
        return mchOrderInfoResult;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getWxPayKey() {
        return wxPayKey;
    }

    public void setWxPayKey(String wxPayKey) {
        this.wxPayKey = wxPayKey;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
