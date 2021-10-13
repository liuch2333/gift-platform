package com.gift.tools.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 工具类
 * @createTime 2021年07月25日 10:36
 */
public class Condition {


    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param entity 实体
     * @param <T>    类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }


    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query 查询条件
     * @param clazz 实体类
     * @param <T>   类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {
        List<String> exclude = Arrays.asList("current","size","ascs","descs");
        return getQueryWrapper(query, exclude, clazz, new HashMap<>());
    }

    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query 查询条件
     * @param clazz 实体类
     * @param <T>   类型
     * @param comparison 字段比对条件
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz,Map<String, Object> comparison) {
        List<String> exclude = Arrays.asList("current","size","ascs","descs");
        return getQueryWrapper(query, exclude, clazz,comparison);
    }


    /**
     * 获取mybatis plus中的QueryWrapper
     *
     * @param query   查询条件
     * @param exclude 排除的查询条件
     * @param clazz   实体类
     * @param <T>     类型
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, List<String> exclude, Class<T> clazz,Map<String, Object> comparison) {
        exclude.forEach(query::remove);
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.setEntity(BeanUtils.instantiateClass(clazz));
        SqlKeyword.buildCondition(query, qw,comparison);
        return qw;
    }
}
