package com.hanm.weixin.weixinpay.controller;

import com.alibaba.fastjson.JSON;
import com.hanm.weixin.weixinpay.controller.vo.OrderVO;
import com.hanm.weixin.weixinpay.controller.vo.WxJsConfigVO;
import com.hanm.weixin.weixinpay.model.TdOrder;
import com.hanm.weixin.weixinpay.service.WeiChatPayService;
import com.hanm.weixin.weixinpay.service.WeiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.util.MapUtil;
import weixin.popular.util.SignatureUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 张钟
 * @date 2018/4/24
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private WeiChatService weiChatService;

    @Autowired
    private WeiChatPayService weiChatPayService;


    @RequestMapping(value = "/bill/")
    public String submit(HttpSession session, Model model){
        TdOrder tdOrder = new TdOrder();
        String openId = String.valueOf(session.getAttribute("openId"));
        Unifiedorder unifiedorder = weiChatPayService.buildUnifiedorder(openId);
        UnifiedorderResult unifiedorderResult =  weiChatPayService.payUnifiedorder(unifiedorder);
        model.addAttribute("unifiedorderResult",unifiedorderResult);
        model.addAttribute("orderResult", JSON.toJSONString(unifiedorder));

        Map<String,String> orderParam = new HashMap<>();
        orderParam.put("appId",weiChatPayService.getAppId());
        orderParam.put("timeStamp",System.currentTimeMillis()+"");
        orderParam.put("nonceStr", UUID.randomUUID().toString().replace("-", "").toUpperCase());
        orderParam.put("package","prepay_id"+unifiedorderResult.getPrepay_id());
        orderParam.put("signType","MD5");
        String sign = SignatureUtil.generateSign(orderParam,unifiedorder.getSign_type(),weiChatPayService.getWxPayKey());
        orderParam.put("paySign",sign);

        WxJsConfigVO wxJsConfigVO = new WxJsConfigVO();
        wxJsConfigVO.setAppId(weiChatPayService.getAppId());
        Map<String,String> wxConfig = MapUtil.objectToMap(unifiedorder);
        wxConfig.remove("signature");
        String signature = SignatureUtil.generateSign(wxConfig,unifiedorder.getSign_type(),weiChatPayService.getWxPayKey());
        wxJsConfigVO.setSignature(signature);
        model.addAttribute("wxJsConfigVO",wxJsConfigVO);

        OrderVO orderVO = new OrderVO();
        orderVO.setAppId(orderParam.get("appId"));
        orderVO.setTimeStamp(orderParam.get("timeStamp"));
        orderVO.setNonceStr(orderParam.get("nonceStr"));
        orderVO.set_package(orderParam.get("package"));
        orderVO.setSignType(orderParam.get("signType"));
        orderVO.setPaySign(orderParam.get("paySign"));

        model.addAttribute("order",orderVO);

        return "order/order";
    }

}
