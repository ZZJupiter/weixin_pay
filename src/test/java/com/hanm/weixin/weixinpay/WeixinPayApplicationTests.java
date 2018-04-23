package com.hanm.weixin.weixinpay;

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

}
