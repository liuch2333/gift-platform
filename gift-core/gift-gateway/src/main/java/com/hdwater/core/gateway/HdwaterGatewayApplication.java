package com.gift.core.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class giftGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(giftGatewayApplication.class, args);
    }

}
