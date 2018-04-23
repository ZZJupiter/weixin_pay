package com.hanm.weixin.weixinpay.service;

import com.alibaba.fastjson.JSON;
import com.hanm.weixin.weixinpay.intergation.AccessTokenResult;
import com.hanm.weixin.weixinpay.intergation.OpenIdResult;
import com.hanm.weixin.weixinpay.intergation.WeiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 张钟 on 2017/9/27.
 */

@Service
public class WeiChatService {

    @Value("${wx.xcx.appId}")
    private String appId;

    @Value("${wx.xcx.secret}")
    private String secret;

    private String authorization_code = "authorization_code";

    @Autowired
    private WeiChatClient weiChatClient;

    /**
     * 通过jsCode换取openId
     *
     * @param jsCode
     * @return
     */
    public String queryOpenId(String jsCode) {
        String result = weiChatClient.queryOpenId(appId, secret, jsCode, authorization_code);
        System.out.println("换取openid结果:"+result);
        OpenIdResult openIdResult = JSONObject.toJavaObject(JSONObject.parseObject(result),
                OpenIdResult.class);
        return openIdResult.getOpenid();
    }

    public AccessTokenResult queryAccessToken(String code){
        String tempResult = weiChatClient.getAccessToken(appId,secret,code,authorization_code);
        AccessTokenResult result = JSON.parseObject(tempResult,AccessTokenResult.class);
        return result;
    }



}
