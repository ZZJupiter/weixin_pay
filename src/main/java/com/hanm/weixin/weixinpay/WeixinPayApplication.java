package com.hanm.weixin.weixinpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeixinPayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeixinPayApplication.class, args);
	}
}
