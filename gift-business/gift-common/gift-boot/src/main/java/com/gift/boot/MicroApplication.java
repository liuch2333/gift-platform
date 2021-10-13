package com.gift.boot;

import com.gift.boot.constants.AppConstant;
import com.gift.boot.constants.NacosConstant;
import com.gift.boot.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description  项目启动器，设置默认环境变量
 * @createTime 2021年07月25日 16:45
 */
public class MicroApplication {


    public static ConfigurableApplicationContext run(String appName, Class source, String... args) {
        SpringApplicationBuilder builder = createSpringApplicationBuilder(appName, source, args);
        return builder.run(args);
    }

    private static SpringApplicationBuilder createSpringApplicationBuilder(String appName, Class source, String[] args) {

        // 读取环境变量，使用spring boot的规则
        ConfigurableEnvironment environment = new StandardEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new SimpleCommandLinePropertySource(args));
        propertySources.addLast(new MapPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, environment.getSystemProperties()));
        propertySources.addLast(new SystemEnvironmentPropertySource(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, environment.getSystemEnvironment()));
        // 获取配置的环境变量
        String[] activeProfiles = environment.getActiveProfiles();
        // 判断环境:dev、test、prod
        List<String> profiles = Arrays.asList(activeProfiles);
        // 预设的环境
        List<String> presetProfiles = new ArrayList<>(Arrays.asList(AppConstant.DEV_CODE, AppConstant.TEST_CODE, AppConstant.PROD_CODE));
        // 交集
        presetProfiles.retainAll(profiles);
        // 当前使用
        List<String> activeProfileList = new ArrayList<>(profiles);

        SpringApplicationBuilder builder = new SpringApplicationBuilder(source);
        String profile;
        if (activeProfileList.isEmpty()) {
            // 默认dev开发
            profile = AppConstant.DEV_CODE;
            activeProfileList.add(profile);
            builder.profiles(profile);
        } else if (activeProfileList.size() == 1) {
            profile = activeProfileList.get(0);
        } else {
            // 同时存在dev、test、prod环境时
            throw new RuntimeException("同时存在环境变量:[" + StringUtils.arrayToCommaDelimitedString(activeProfiles) + "]");
        }
        String startJarPath = MicroApplication.class.getResource("/").getPath().split("!")[0];
        String activePros = StringUtils.arrayToCommaDelimitedString(activeProfileList.toArray());
        System.out.printf("----启动中，读取到的环境变量:[%s]，jar地址:[%s]----%n", activePros, startJarPath);
        Properties props = System.getProperties();
        props.setProperty("spring.application.name", appName);
        props.setProperty("spring.profiles.active", profile);
        Properties defaultProperties = new Properties();
        defaultProperties.setProperty("spring.main.allow-bean-definition-overriding", "true");
        defaultProperties.setProperty("spring.cloud.nacos.discovery.server-addr",  NacosConstant.nacosAddr(profile));
        defaultProperties.setProperty("spring.cloud.nacos.config.server-addr",  NacosConstant.nacosAddr(profile));
        defaultProperties.setProperty("spring.cloud.nacos.config.file-extension", NacosConstant.NACOS_CONFIG_FORMAT);
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[0].data-id", NacosConstant.sharedDataId());
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[0].group", NacosConstant.NACOS_CONFIG_GROUP);
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[0].refresh", NacosConstant.NACOS_CONFIG_REFRESH);
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[1].data-id", NacosConstant.sharedDataId(profile));
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[1].group", NacosConstant.NACOS_CONFIG_GROUP);
        defaultProperties.setProperty("spring.cloud.nacos.config.shared-configs[1].refresh", NacosConstant.NACOS_CONFIG_REFRESH);
        builder.properties(defaultProperties);
        // 加载自定义组件
        List<LauncherService> launcherList = new ArrayList<>();
        ServiceLoader.load(LauncherService.class).forEach(launcherList::add);
        launcherList.stream().sorted(Comparator.comparing(LauncherService::getOrder)).collect(Collectors.toList())
                .forEach(launcherService -> launcherService.launcher(builder, appName, profile));
        return builder;
    }
}
