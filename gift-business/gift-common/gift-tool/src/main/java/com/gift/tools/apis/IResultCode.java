package com.gift.tools.apis;

import java.io.Serializable;

/**
 * 业务代码接口
 * @author liuch
 * @date 2020/10/10 10:09
 */
public interface IResultCode extends Serializable {

    /**
     * 获取消息
     *
     * @return
     */
    String getMessage();

    /**
     * 获取状态码
     *
     * @return
     */
    int getCode();
}
