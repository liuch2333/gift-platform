package com.gift.tools.configs.redis.common;

/**
 * @author liuch
 * @title: PropertyConstants
 * @description: redis属性配置key
 * @date 2021/8/8 11:02
 */
public interface RedisBasicConstant {
    /**
     * redis分组 例：  HD:PROPERTY
     */
    String TOP_GROUP="HD:";
    /**
     * @Author liuch
     * @Description 数据字典
     * @Date 2021/8/23 15:17
     * @param
     * @return
     */
    String PRO_PREFIX = TOP_GROUP+"PROPERTY";
    /**
     * @Author liuch
     * @Description 业务类别
     * @Date 2021/8/23 15:18
     * @param
     * @return
     */
    String BUSINESS_PREFIX = TOP_GROUP+"BUSINESS";
    /**
     * @Author liuch
     * @Description 业务材料配置
     * @Date 2021/8/23 15:18
     * @param
     * @return
     */
    String DATUM_PREFIX = TOP_GROUP+"DATUM";
    /**
     * @Author liuch
     * @Description 往来单位信息
     * @Date 2021/8/23 15:18
     * @param
     * @return
     */
    String EXRTERNAL_COMPANY_PREFIX = TOP_GROUP+"EXRTERNAL";

    /**
     * @Author liuch
     * @Description 往来人员信息
     * @Date  15:20
     * @param
     * @return
     */
    String EXRTERNAL_PERSON_PREFIX = EXRTERNAL_COMPANY_PREFIX+":PERSON";

    /**
     * @Author liuch
     * @Description 客户信息
     * @Date 2021/8/29 15:18
     * @param
     * @return
     */
    String CUSTOMER = TOP_GROUP+"CUSTOMER";
}
