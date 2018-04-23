package com.hanm.weixin.weixinpay.controller;

import javax.servlet.http.HttpSession;

import com.hanm.weixin.weixinpay.intergation.AccessTokenResult;
import com.hanm.weixin.weixinpay.service.WeiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张钟
 * @date 2018/4/23
 */
@Controller
public class IndexController {

    @Autowired
    private WeiChatService weiChatService;

    @RequestMapping(value = "/")
    public String index(HttpSession session, String code, String state, Model model) {
        session.setAttribute("code", code);
        session.setAttribute("state", state);
        model.addAttribute("code", code);
        model.addAttribute("state", state);

        return "index";
    }

    @RequestMapping(value = "/accessToken")
    public String getAccessToken(HttpSession session,Model model){
        String code = String.valueOf(session.getAttribute("code"));
        AccessTokenResult result = weiChatService.queryAccessToken(code);
        model.addAttribute("accessToken",result.getAccess_token());
        session.setAttribute("accessToken",result.getAccess_token());
        model.addAttribute("openId",result.getOpenid());
        session.setAttribute("openId",result.getOpenid());
        return "accessToken";
    }

}
