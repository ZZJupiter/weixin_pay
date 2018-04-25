package com.hanm.weixin.weixinpay.controller;

import javax.servlet.http.HttpSession;

import com.hanm.weixin.weixinpay.intergation.AccessTokenBaseResult;
import com.hanm.weixin.weixinpay.intergation.AccessTokenResult;
import com.hanm.weixin.weixinpay.intergation.TicketResult;
import com.hanm.weixin.weixinpay.service.WeiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 张钟
 * @date 2018/4/23
 */
@Controller
public class IndexController {

    @Autowired
    private WeiChatService weiChatService;

    @ResponseBody
    @RequestMapping(value = "/")
    public String home(){
        return "hello";
    }

    @RequestMapping(value = "/index")
    public String index(HttpSession session, String code, String state, Model model) {
        session.setAttribute("code", code);
        session.setAttribute("state", state);
        model.addAttribute("code", code);
        model.addAttribute("state", state);
        return "index";
    }

    @RequestMapping(value = "/accessToken")
    public String getAccessToken(HttpSession session, Model model) {
        String code = String.valueOf(session.getAttribute("code"));

        AccessTokenResult result = weiChatService.queryAccessToken(code);
        model.addAttribute("openId", result.getOpenid());
        session.setAttribute("openId", result.getOpenid());

        AccessTokenBaseResult accessToken = weiChatService.queryAccessTokenBase();
        session.setAttribute("accessToken", accessToken.getAccess_token());
        model.addAttribute("accessToken",accessToken.getAccess_token());

        String access_token = String.valueOf(session.getAttribute("accessToken"));
        TicketResult ticket = weiChatService.queryTicket(access_token);
        model.addAttribute("ticket", ticket.getTicket());
        session.setAttribute("ticket", ticket.getTicket());

        return "accessToken";
    }


}
