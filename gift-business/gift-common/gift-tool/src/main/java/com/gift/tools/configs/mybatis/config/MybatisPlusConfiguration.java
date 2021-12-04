package com.gift.tools.configs.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.gift.tools.configs.mybatis.interceptor.SqlLogInterceptor;
import com.gift.tools.configs.mybatis.props.MybatisPlusProperties;
import lombok.AllArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description mybatis-plus 配置
 * @createTime 2021年07月25日 10:16
 */

@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
@MapperScan("com.gift.**.mapper.**")
public class MybatisPlusConfiguration {



    /**
     * mybatis plus 拦截器配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页支持
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * sql 日志
     */
    @Bean
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }
}
