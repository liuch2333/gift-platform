package com.gift.baseinfo.main.service.impl;

import com.gift.baseinfo.main.conf.FileProperties;
import com.gift.baseinfo.main.entity.FileInfoVo;
import com.gift.baseinfo.main.filemanage.FileManage;
import com.gift.baseinfo.main.service.CommonService;
import com.gift.tools.apis.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author liuch
 * @title: CommonServiceImpl
 * @description: TODO
 * @date 2021/10/17 10:45
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private Map<String, FileManage> fileManageMap;

    @Override
    public R upload(MultipartFile[] files) {
        //获取附件管理实现类
        FileManage fileManage = getFileManage(fileProperties.getModel());
        List<FileInfoVo> infoVos = fileManage.upload(files);
        return R.data(infoVos);
    }

    /**
     * @param fileModel
     * @return com.hd.basic.filemanage.FileManage
     * @Author liuch
     * @Description 根据文件存储模式，获取文件管理对象
     * @Date 2021/8/31 15:12
     */
    public FileManage getFileManage(String fileModel) {
        String name = fileModel + FileManage.class.getSimpleName();
        FileManage fileManage = fileManageMap.get(name);
        //如果没有对应的，就取本地配置
        if (null == fileManage) {
            fileManage = fileManageMap.get("local" + FileManage.class.getSimpleName());
        }
        return fileManage;
    }
}
