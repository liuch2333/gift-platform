package com.gift.boot.service;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.Ordered;

/**
 * @Description 启动配置扩展 用于一些组件配置
 * @createTime 2021年07月25日 22:44
 */
public interface LauncherService extends Ordered, Comparable<LauncherService>{


    /**
     * 启动时 处理 SpringApplicationBuilder
     *
     * @param builder    SpringApplicationBuilder
     * @param appName    服务名
     * @param profile    环境变量
     */
    void launcher(SpringApplicationBuilder builder, String appName, String profile);

    /**
     * 获取排列顺序
     *
     * @return order
     */
    @Override
    default int getOrder() {
        return 0;
    }

    /**
     * 对比排序
     *
     * @param o LauncherService
     * @return compare
     */
    @Override
    default int compareTo(LauncherService o) {
        return Integer.compare(this.getOrder(), o.getOrder());
    }

}
