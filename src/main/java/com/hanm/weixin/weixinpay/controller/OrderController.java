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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author 张钟
 * @date 2018/4/23
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private WeiChatService weiChatService;

    @Autowired
    private WeiChatPayService weiChatPayService;


    @RequestMapping(value = "/submit/pay.htm")
    public String submit(HttpSession session, Model model) {
        TdOrder tdOrder = new TdOrder();
        String openId = String.valueOf(session.getAttribute("openId"));
        Unifiedorder unifiedorder = weiChatPayService.buildUnifiedorder(openId);
        UnifiedorderResult unifiedorderResult = weiChatPayService.payUnifiedorder(unifiedorder);
        model.addAttribute("prepay_id", unifiedorderResult.getPrepay_id());

        WxJsConfigVO wxJsConfigVO = new WxJsConfigVO();
        wxJsConfigVO.setAppId(weiChatPayService.getAppId());

        //生成jsapi的签名
        wxJsConfigVO.setSignature(generateSignature(wxJsConfigVO, String.valueOf(session.getAttribute("ticket"))));
        model.addAttribute("wxJsConfigVO", wxJsConfigVO);

        Map<String, String> orderParam = new HashMap<>();
        orderParam.put("appId", weiChatPayService.getAppId());
        orderParam.put("timeStamp", System.currentTimeMillis() + "");
        orderParam.put("nonceStr", UUID.randomUUID().toString().replace("-", "").toUpperCase());
        orderParam.put("package", "prepay_id=" + unifiedorderResult.getPrepay_id());
        orderParam.put("signType", "MD5");

        String sign = SignatureUtil.generateSign(orderParam,unifiedorder.getSign_type(),weiChatPayService.getWxPayKey());
//        String sign = generatePaySingnature(weiChatPayService.getAppId(), orderParam.get("nonceStr"), orderParam.get("package"), orderParam.get("timeStamp"));
        orderParam.put("paySign", sign);

        OrderVO orderVO = new OrderVO();
        orderVO.setAppId(orderParam.get("appId"));
        orderVO.setTimeStamp(orderParam.get("timeStamp"));
        orderVO.setNonceStr(orderParam.get("nonceStr"));
        orderVO.set_package(orderParam.get("package"));
        orderVO.setSignType(orderParam.get("signType"));
        orderVO.setPaySign(orderParam.get("paySign"));

        model.addAttribute("order", orderVO);

        return "order/order";
    }

    String generatePaySingnature(String appId, String nonceStr, String _package, String timeStamp) {
        String tempString = "appId=" + appId + "&nonceStr=" + nonceStr + "&package=" + _package + "&signType=SHA1&timeStamp=" + timeStamp;
        String signature = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(tempString.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }

    private String generateSignature(WxJsConfigVO wxJsConfigVO, String jsapi_ticket) {
        // 注意这里参数名必须全部小写，且必须有序
        String tempStr = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + wxJsConfigVO.getNonceStr()
                + "&timestamp=" + wxJsConfigVO.getTimestamp() + "&url=" + "http://weixin.exuekao.cn/order/submit/pay.htm?";

        String signature = null;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(tempStr.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }


    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
