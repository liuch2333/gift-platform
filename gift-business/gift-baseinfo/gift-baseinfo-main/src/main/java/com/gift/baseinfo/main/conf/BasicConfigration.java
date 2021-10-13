package com.gift.baseinfo.main.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuch
 * @title: BasicConfigration
 * @description: 自动配置类
 * @date 2021/8/31 15:45
 */
@Configuration
public class BasicConfigration {

    @Bean
    public FileProperties fileProperties(){
        return new FileProperties();
    }


}
