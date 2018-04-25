package com.hanm.weixin.weixinpay.intergation;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * Created by 张钟 on 2017/9/27.
 */
@Component
public class WeiChatClientFailBack implements FallbackFactory<WeiChatClient> {

    @Override
    public WeiChatClient create(Throwable cause) {
        return new WeiChatClient() {
            @Override
            public String queryOpenId(String appid, String secret, String js_code,
                                      String grant_type) {
                System.out.println("huoqu openId yichang");
                return null;
            }

            @Override
            public String getAccessToken(String appid, String secret, String code, String grant_type) {
                System.out.println("huoqu access token fail");
                return null;
            }

            @Override
            public String getAccessTokenBase(String grant_type, String appid, String secret) {
                System.out.println("huoqu access token fail");
                return null;            }

            @Override
            public String getTicket(String access_token, String type) {
                System.out.println("huoqu access token fail");
                return null;
            }
        };
    }
}
