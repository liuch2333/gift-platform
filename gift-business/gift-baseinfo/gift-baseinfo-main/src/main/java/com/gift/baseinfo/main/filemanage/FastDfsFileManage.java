package com.gift.baseinfo.main.filemanage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liuch
 * @title: FastDfsFileManage
 * @description: fastdfs文件管理
 * @date 2021/8/31 14:28
 */
@Service("fastDfsFileManage")
public class FastDfsFileManage implements FileManage {

    @Override
    public List upload(MultipartFile[] files) {
        return null;
    }

    @Override
    public String fileDownload(String path) {
        return null;
    }

    @Override
    public void delFile(String path) {

    }
}
