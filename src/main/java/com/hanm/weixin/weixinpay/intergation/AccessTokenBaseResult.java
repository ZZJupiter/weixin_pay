package com.hanm.weixin.weixinpay.intergation;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jianyi
 * \* Date: 2018/4/25
 * \* Time: 上午9:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class AccessTokenBaseResult {

    private String access_token;
    private int expires_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getExpires_in() {
        return expires_in;
    }
}
