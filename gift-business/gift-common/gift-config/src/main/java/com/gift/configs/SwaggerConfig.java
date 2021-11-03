package com.gift.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration()
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.nameText}")
    private String nameText;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
//                .paths(Predicates.not(PathSelectors.regex("/error")))
                .paths(PathSelectors.regex("/.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("礼品平台接口文档")
                .description("礼品平台接口文档")
                .contact(new Contact("刘成辉","","1205386897@qq.com"))
                .version("1.0")
                .build();
    }
    /*@Bean
    public Docket createRestApi() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("1.0版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.github.xiaoymin.knife4j.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目接口文档")
                .description("服务相关接口")
                .contact(new Contact("刘成辉","","1205386897@qq.com"))
                .version("1.0")
                .build();
    }*/

}