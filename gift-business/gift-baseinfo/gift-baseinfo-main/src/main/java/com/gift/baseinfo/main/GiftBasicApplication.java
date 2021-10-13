package com.gift.baseinfo.main;

import com.gift.boot.MicroApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liuch
 * @description 礼品信息
 * @date 2021/10/7 21:27
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.gift.**.mapper")
public class GiftBasicApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GiftBasicApplication.class, args);
        MicroApplication.run("gift-baseinfo",GiftBasicApplication.class, args);
    }

}
