package com.gift.baseinfo.main.filemanage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author liuch
 * @title: HdfsFileManage
 * @description: fdfs文件管理
 * @date 2021/8/31 14:28
 */
@Service("hdfsFileManage")
public class HdfsFileManage implements FileManage {

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
