package com.hanm.weixin.weixinpay;

import com.hanm.weixin.weixinpay.intergation.AccessTokenBaseResult;
import com.hanm.weixin.weixinpay.intergation.TicketResult;
import com.hanm.weixin.weixinpay.service.WeiChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeixinPayApplicationTests {

    @Autowired
    private WeiChatService weiChatService;

    @Test
    public void contextLoads() {
        weiChatService.queryAccessToken("xx");
    }

    @Test
    public void getTicket() {
        TicketResult ticketResult = weiChatService.queryTicket(
                "9_3R-tBVcvdsUb4rJe5RNSw7YP2IYgfnxJs3h6vXw4LeQ8ak4-9N0_9IafZmijMb-Ox1xIKEqzugwBEBfTL0AX2x4mGObu42W5T23IGLb7NZzJDq5DxaDWdPDTzPEYSWbAEAPJN");
        System.out.println(ticketResult);
    }


    @Test
    public void getTicketBase() {
        AccessTokenBaseResult ticket = weiChatService.queryAccessTokenBase();
        System.out.println(ticket);
    }

}
