package com.hanm.weixin.weixinpay.intergation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 张钟 on 2017/9/27.
 */
@FeignClient(name = "openIdClient", url = "https://api.weixin.qq.com", fallbackFactory = WeiChatClientFailBack.class)
public interface WeiChatClient {

    /**
     * //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
     * appid	是	小程序唯一标识
     * secret	是	小程序的 app secret
     * js_code	是	登录时获取的 code
     * grant_type	是	填写为 authorization_code
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/sns/jscode2session")
    String queryOpenId(@RequestParam("appid") String appid, @RequestParam("secret") String secret,
                       @RequestParam("js_code") String js_code,
                       @RequestParam("grant_type") String grant_type);

    @RequestMapping(method = RequestMethod.GET, value = "/sns/oauth2/access_token")
    String getAccessToken(@RequestParam("appid") String appid,
                          @RequestParam("secret") String secret, @RequestParam("code") String code,
                          @RequestParam("grant_type") String grant_type);

    //grant_type=client_credential&appid=APPID&secret=APPSECRET
    @RequestMapping(method = RequestMethod.GET, value = "/cgi-bin/token")
    String getAccessTokenBase(@RequestParam("grant_type") String grant_type,
                              @RequestParam("appid") String appid,
                              @RequestParam("secret") String secret);

    @RequestMapping(method = RequestMethod.GET, value = "/cgi-bin/ticket/getticket")
    String getTicket(@RequestParam("access_token") String access_token,
                     @RequestParam("type") String type);

}
