package com.hanm.weixin.weixinpay.controller.vo;

import java.util.UUID;

/**
 * @author 张钟
 * @date 2018/4/23
 */
public class WxJsConfigVO {

    private boolean debug = true;

    private String appId;

    private String timestamp = System.currentTimeMillis()+"";

    private String nonceStr = UUID.randomUUID().toString().replace("-", "").toUpperCase();

    private String signature;

    private String[] jsApiList = "chooseWXPay".split(",");


    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String[] getJsApiList() {
        return jsApiList;
    }

    public void setJsApiList(String[] jsApiList) {
        this.jsApiList = jsApiList;
    }
}
