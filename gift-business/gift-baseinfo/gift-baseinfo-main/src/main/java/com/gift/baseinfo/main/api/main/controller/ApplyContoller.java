package com.gift.baseinfo.main.api.main.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuch
 * @title: ApplyContoller
 * @description: TODO
 * @date 2021/7/24 15:57
 */
@RestController
@RequestMapping("/apply/")
@Log4j2
public class ApplyContoller {

    @GetMapping(value = "hello")
    public String hello(@RequestParam("name") String name) {
        log.info("*************"+name+"*************");
        return "hello:" + name;
    }
}
