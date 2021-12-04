package com.gift.baseinfo.main.service;

import com.gift.tools.apis.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liuch
 * @title: CommonService
 * @description: 其他服务
 * @date 2021/10/17 10:44
 */
public interface CommonService {

    /**
     * @author liuch
     * @description 单独上传资料，保存上传数据，不对应条目信息
     * @date 2021/9/17 9:24
     * @param
     * @param files
     * @return com.hd.tool.api.R 只返回本次上传结果
     */
    R upload(MultipartFile[] files);

    /**
     * @Author liuch
     * @Description 预览附件
     * @Date 2021/8/31 18:45
     * @param id
     * @return com.hd.tool.api.R
     */
    void previewFile(String id, HttpServletResponse response);
}
