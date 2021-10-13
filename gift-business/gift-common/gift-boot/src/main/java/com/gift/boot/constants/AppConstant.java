package com.gift.boot.constants;

/**
 * @Description 系统常量
 * @createTime 2021年07月25日 22:34
 */
public interface AppConstant {

    /**
     * 开发环境
     */
    String DEV_CODE = "dev";
    /**
     * 生产环境
     */
    String PROD_CODE = "prod";
    /**
     * 测试环境
     */
    String TEST_CODE = "test";

    /**
     * 基础包
     */
    String BASE_PACKAGES = "com.hd";

    /**
     * 版本
     */
    String APPLICATION_VERSION = "0.0.1";

    /**
     * 应用名前缀
     */
    String APPLICATION_NAME_PREFIX = "hd-";


    /**
     * 系统模块名称
     */
    String APPLICATION_SYSTEM_NAME = APPLICATION_NAME_PREFIX + "system";


    /**
     * 系统模块名称
     */
    String APPLICATION_ENGIN_NAME = APPLICATION_NAME_PREFIX + "engin";


    /**
     * 路由模块
     */
    String APPLICATION_GATEWAY_NAME = APPLICATION_NAME_PREFIX + "gateway";

    /**
     * 基础数据
     */
    String APPLICATION_BASIC_NAME = APPLICATION_NAME_PREFIX + "basic";


}
