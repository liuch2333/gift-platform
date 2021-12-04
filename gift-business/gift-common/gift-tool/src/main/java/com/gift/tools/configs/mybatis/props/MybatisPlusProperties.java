package com.gift.tools.configs.mybatis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  MybatisPlus配置类
 * @createTime 2021年07月25日 10:18
 */

@Data
public class MybatisPlusProperties {


    /**
     * 开启sql日志
     */
    private Boolean sqlLog = true;

    /**
     * sql日志忽略打印关键字
     */
    private List<String> sqlLogExclude = new ArrayList<>();


}
