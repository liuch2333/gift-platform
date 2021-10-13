package com.gift.tools.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.util.Map;

/**
 * @Description 定义常用的 sql关键字
 * @createTime 2021年07月25日 13:12
 */
public class SqlKeyword {


    private final static String SQL_REGEX = "'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql";

    public static final String EQUAL = "_equal";
    public static final String NOT_EQUAL = "_notequal";
    public static final String LIKE = "_like";
    public static final String LIKE_LEFT = "_likeleft";
    public static final String LIKE_RIGHT = "_likeright";
    public static final String NOT_LIKE = "_notlike";
    public static final String GE = "_ge";
    public static final String LE = "_le";
    public static final String GT = "_gt";
    public static final String LT = "_lt";
    public static final String DATE_GE = "_datege";
    public static final String DATE_GT = "_dategt";
    public static final String DATE_EQUAL = "_dateequal";
    public static final String DATE_LT = "_datelt";
    public static final String DATE_LE = "_datele";
    public static final String IS_NULL = "_null";
    public static final String NOT_NULL = "_notnull";
    public static final String IGNORE = "_ignore";

    /**
     * 条件构造器
     *
     * @param query 查询字段
     * @param qw    查询包装类
     */
    public static void buildCondition(Map<String, Object> query, QueryWrapper<?> qw,Map<String, Object> comparison) {
        if (ObjectUtil.isEmpty(query)) {
            return;
        }
        query.forEach((k, v) -> {

            if(ObjectUtil.hasEmpty(k,v) || k.endsWith(IGNORE)){
                return;
            }
            String compare = MapUtil.getStr(comparison,k);

            if (k.endsWith(EQUAL) || EQUAL.equals(compare)) {
                qw.eq(getColumn(k, EQUAL), v);
            }else if (k.endsWith(LIKE) || LIKE.equals(compare)) {
                qw.like(getColumn(k, LIKE), v);
            }else if (k.endsWith(NOT_EQUAL) || NOT_EQUAL.equals(compare)) {
                qw.ne(getColumn(k, NOT_EQUAL), v);
            } else if (k.endsWith(LIKE_LEFT) || LIKE_LEFT.equals(compare)) {
                qw.likeLeft(getColumn(k, LIKE_LEFT), v);
            } else if (k.endsWith(LIKE_RIGHT) || LIKE_RIGHT.equals(compare)) {
                qw.likeRight(getColumn(k, LIKE_RIGHT), v);
            } else if (k.endsWith(NOT_LIKE) || NOT_LIKE.equals(compare)) {
                qw.notLike(getColumn(k, NOT_LIKE), v);
            } else if (k.endsWith(GE) || GE.equals(compare)) {
                qw.ge(getColumn(k, GE), v);
            } else if (k.endsWith(LE) || LE.equals(compare)) {
                qw.le(getColumn(k, LE), v);
            } else if (k.endsWith(GT) || GT.equals(compare)) {
                qw.gt(getColumn(k, GT), v);
            } else if (k.endsWith(LT) || LT.equals(compare)) {
                qw.lt(getColumn(k, LT), v);
            } else if (k.endsWith(DATE_GE) || DATE_GE.equals(compare)) {
                qw.ge(getColumn(k, DATE_GE), DateUtil.parseDateTime(String.valueOf(v)));
            } else if (k.endsWith(DATE_GT) || DATE_GT.equals(compare)) {
                qw.gt(getColumn(k, DATE_GT),  DateUtil.parseDateTime(String.valueOf(v)));
            } else if (k.endsWith(DATE_EQUAL) || DATE_EQUAL.equals(compare)) {
                qw.eq(getColumn(k, DATE_EQUAL),  DateUtil.parseDateTime(String.valueOf(v)));
            } else if (k.endsWith(DATE_LE) || DATE_LE.equals(compare)) {
                qw.le(getColumn(k, DATE_LE),  DateUtil.parseDateTime(String.valueOf(v)));
            } else if (k.endsWith(DATE_LT) || DATE_LT.equals(compare)) {
                qw.lt(getColumn(k, DATE_LT),  DateUtil.parseDateTime(String.valueOf(v)));
            } else if (k.endsWith(IS_NULL) || IS_NULL.equals(compare)) {
                qw.isNull(getColumn(k, IS_NULL));
            } else if (k.endsWith(NOT_NULL) || NOT_NULL.equals(compare)) {
                qw.isNotNull(getColumn(k, NOT_NULL));
            } else {
                qw.eq(getColumn(k, EQUAL), v);
            }
        });
    }

    /**
     * 获取数据库字段
     *
     * @param column  字段名
     * @param keyword 关键字
     * @return
     */
    private static String getColumn(String column, String keyword) {
        return StrUtil.toUnderlineCase(StrUtil.removeSuffix(column, keyword));
    }

    /**
     * 把SQL关键字替换为空字符串
     *
     * @param param 关键字
     * @return string
     */
    public static String filter(String param) {
        if (param == null) {
            return null;
        }
        return param.replaceAll("(?i)" + SQL_REGEX, StringPool.EMPTY);
    }
}
