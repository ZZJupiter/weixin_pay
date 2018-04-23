package com.hanm.weixin.weixinpay.intergation;

/**
 * Created by 张钟 on 2017/9/27.
 */
public class OpenIdResult {

    private String session_key;

    private int    expires_in;

    private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
