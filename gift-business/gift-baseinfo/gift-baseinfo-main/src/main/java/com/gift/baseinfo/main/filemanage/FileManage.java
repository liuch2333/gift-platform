package com.gift.baseinfo.main.filemanage;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liuch
 * @title: FileManage
 * @description: 文件管理
 * @date 2021/8/31 14:19
 */
public interface FileManage {

    /**
     * @Author liuch
     * @Description 批量文件上传
     * @Date 2021/8/31 14:23
     * @param files
     * @return java.lang.String
     */
    public abstract List upload(MultipartFile[] files);

    /**
     * @Author liuch
     * @Description 文件下载
     * @Date 2021/8/31 14:23
     * @param path
     * @return java.lang.String
     */
    public abstract String fileDownload(String path);

    /**
     * @Author liuch
     * @Description 删除文件
     * @Date 2021/8/31 14:24
     * @params [path]
     * @return void
     */
    public abstract void delFile(String path);
}
