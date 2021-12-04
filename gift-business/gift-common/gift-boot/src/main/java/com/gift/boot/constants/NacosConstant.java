package com.gift.boot.constants;

/**
 * @Description Nacos常量
 * @createTime 2021年07月25日 22:39
 */
public interface NacosConstant {

    /**
     * nacos dev 地址
     */
    String NACOS_DEV_ADDR = "127.0.0.1:8848";

    /**
     * nacos prod 地址
     */
    String NACOS_PROD_ADDR = "127.0.0.1:8848";

    /**
     * nacos test 地址
     */
    String NACOS_TEST_ADDR = "192.168.100.132:8848";


    /**
     * nacos 配置前缀
     */
    String NACOS_CONFIG_PREFIX = "micro";

    /**
     * nacos 组配置后缀
     */
    String NACOS_GROUP_SUFFIX = "-group";

    /**
     * nacos 配置文件类型
     */
    String NACOS_CONFIG_FORMAT = "yaml";

    /**
     * nacos json配置文件类型
     */
    String NACOS_CONFIG_JSON_FORMAT = "json";

    /**
     * nacos 分组
     */
    String NACOS_CONFIG_GROUP = "DEFAULT_GROUP";

    /**
     * nacos 是否刷新
     */
    String NACOS_CONFIG_REFRESH = "true";

    /**
     * 构建服务对应的 dataId
     *
     * @param appName 服务名
     * @return dataId
     */
    static String dataId(String appName) {
        return appName + "." + NACOS_CONFIG_FORMAT;
    }

    /**
     * 构建服务对应的 dataId
     *
     * @param appName 服务名
     * @param profile 环境变量
     * @return dataId
     */
    static String dataId(String appName, String profile) {
        return dataId(appName, profile, NACOS_CONFIG_FORMAT);
    }

    /**
     * 构建服务对应的 dataId
     *
     * @param appName 服务名
     * @param profile 环境变量
     * @param format  文件类型
     * @return dataId
     */
    static String dataId(String appName, String profile, String format) {
        return appName + "-" + profile + "." + format;
    }



    /**
     * 服务默认加载的配置,所有环节共用
     * hd.yml
     * @return sharedDataIds
     */
    static String sharedDataId() {
        return NACOS_CONFIG_PREFIX + "." + NACOS_CONFIG_FORMAT;
    }

    /**
     * 服务默认加载的配置,按环境变量,分开发,测试,正式环境
     * hd-dev.yml
     * @param profile 环境变量
     * @return sharedDataIds
     */
    static String sharedDataId(String profile) {
        return NACOS_CONFIG_PREFIX + "-" + profile + "." + NACOS_CONFIG_FORMAT;
    }

    /**
     * 服务默认加载的配置
     *
     * @param profile 环境变量
     * @return sharedDataIds
     */
    static String sharedDataIds(String profile) {
        return NACOS_CONFIG_PREFIX + "." + NACOS_CONFIG_FORMAT + "," + NACOS_CONFIG_PREFIX + "-" + profile + "." + NACOS_CONFIG_FORMAT;
    }

    static String nacosAddr(String profile) {
        switch (profile) {
            case (AppConstant.PROD_CODE):
                return NACOS_PROD_ADDR;
            case (AppConstant.TEST_CODE):
                return NACOS_TEST_ADDR;
            default:
                return NACOS_DEV_ADDR;
        }
    }
}
