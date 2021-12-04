package com.gift.codegenerator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description mybatis-plus 代码生成器
 * @ClassName CodeGenerator
 * @Author liuch
 * @date 2021.10.19 10:35
 */
public class CodeGenerator {

    /**
     * 注意此main方法在上传到gitlab 之前要注释掉，不然Jenkins 打包的时候多个main方法会报错
     *
     * @param args
     */
    public static void main(String[] args) {
        /**************** 基础配置 ********************/
        // 基础目录
        String packageName = "com.gift.baseinfo.main";
        // 模块名  修改
        String moduleName = "gift-baseinfo";
        // 表名  修改
        String[] tableNames = {"customer"};
        // 作者  修改
        String author = "liuch";


        /**************** 数据源配置 华为云环境 ********************/
        String url = "jdbc:mysql://127.0.0.1:3306/gift?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String driverClass = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "liuch";

        new CodeGenerator().generateByTables(packageName, moduleName, tableNames, author, url, driverClass, userName, password);

    }

    /**
     * 代码生成器
     *
     * @param packageName 基础包名
     * @param moduleName  模块名
     * @param tableNames  表名
     * @param url         数据库url
     * @param driverClass 数据库驱动
     * @param userName    数据库用户名
     * @param password    密码
     */
    public void generateByTables(String packageName, String moduleName, String[] tableNames, String author,
                                 String url, String driverClass, String userName, String password) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir") + "\\gift-common\\gift-dev";
        System.out.println("projectPath:{}" + projectPath);

        // 全局配置
        GlobalConfig globalConfig = setGlobalConfig(projectPath, author);
        //开启Swagger2模式
        globalConfig.setSwagger2(true);
        globalConfig.setFileOverride(true);
        mpg.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = setDataSourceConfig(url, driverClass, userName, password);
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = setPackageConfig(moduleName, packageName);
        mpg.setPackageInfo(packageConfig);

        // 自定义 配置
        InjectionConfig injectionConfig = setInjectionConfig(projectPath, moduleName);
        mpg.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = setTemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = setStrategyConfig(tableNames);
        strategyConfig.setEntityLombokModel(true);
        mpg.setStrategy(strategyConfig);
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 全局配置
     *
     * @param projectPath 工程路径
     * @return
     */
    private GlobalConfig setGlobalConfig(String projectPath, String author) {

        //user -> UserService, 设置成true: user -> IUserService
        boolean serviceNameStartWithI = false;

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(projectPath + "/src/main/java");

        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);

        if (!serviceNameStartWithI) {
            // 自定义文件命名，注意 %s 会自动填充表实体属性！
            // config.setMapperName("%sDao");
            // config.setXmlName("%sDao");
            // config.setServiceName("%sService");
            // config.setServiceImplName("%sServiceDiy");
            // config.setControllerName("%sAction");
            gc.setServiceName("%sService");
        }

        gc.setAuthor(author);
        gc.setOpen(false);
        return gc;
    }


    /**
     * 数据源配置
     *
     * @param url
     * @param driverClass
     * @param userName
     * @param password
     * @return
     */
    private DataSourceConfig setDataSourceConfig(String url, String driverClass, String userName, String password) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverClass);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        return dsc;
    }

    /**
     * 包配置
     *
     * @param moduleName
     * @param packageName
     * @return
     */
    private PackageConfig setPackageConfig(String moduleName, String packageName) {
        PackageConfig pc = new PackageConfig();

        pc.setParent(packageName)
                //.setModuleName(moduleName)
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
//                .setMapper("mapper." + moduleName)
                .setMapper("mapper")
//                .setXml("mapper")
                .setEntity("entity");
        return pc;
    }

    /**
     * 自定义配置
     *
     * @param projectPath
     * @param moduleName
     * @return
     */
    private InjectionConfig setInjectionConfig(String projectPath, String moduleName) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + moduleName + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 策略配置
     *
     * @param tableNames
     * @return
     */
    private StrategyConfig setStrategyConfig(String[] tableNames) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tableNames);
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        return strategy;
    }


    private TemplateConfig setTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setController("templates/liuch.controller.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        return templateConfig;
    }

}
