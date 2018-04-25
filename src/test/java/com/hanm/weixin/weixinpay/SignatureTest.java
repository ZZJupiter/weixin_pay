package com.hanm.weixin.weixinpay;

import com.hanm.weixin.weixinpay.util.Singnature;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: jianyi
 * \* Date: 2018/4/25
 * \* Time: 上午10:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class SignatureTest {

    @Test
    public void sig() {
        String result= Singnature.SHA("jsapi_ticket=HoagFKDcsGMVCIY2vOjf9vzGI61xdMldjHee07mPnw1Q7emn9SYYJhJZHmwysq70m5Jggnpx5bx69Wx&noncestr=9FB11000D5B84F379BC02065A05A3E35&timestamp=1524623118652&url=http://weixin.exuekao.cn/order/submit/");
        System.out.println(result);
    }
}
