package com.gift.baseinfo.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuch
 * @title: ApplyDemoFeignService
 * @description: TODO
 * @date 2021/8/1 17:03
 */
@FeignClient(name = "gift-apply-demo")
public interface ApplyDemoFeignService {

    @GetMapping(value = "/apply/hello")
    String hello(@RequestParam("name") String name);
}
