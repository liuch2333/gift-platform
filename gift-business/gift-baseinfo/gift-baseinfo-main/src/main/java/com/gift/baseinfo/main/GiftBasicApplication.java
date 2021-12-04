package com.gift.baseinfo.main;

import com.gift.boot.MicroApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liuch
 * @description 礼品信息
 * @date 2021/10/7 21:27
 */
@SpringBootApplication(scanBasePackages = {"com.gift.tools.configs","com.gift.baseinfo"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.gift.**.mapper")
public class GiftBasicApplication {

    public static void main(String[] args) {
        MicroApplication.run("gift-baseinfo",GiftBasicApplication.class, args);
    }

}
