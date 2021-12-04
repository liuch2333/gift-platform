package com.gift.baseinfo.main.service.impl;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gift.baseinfo.main.conf.FileProperties;
import com.gift.baseinfo.main.entity.FilemanageVo;
import com.gift.baseinfo.main.enums.FileTypeEnums;
import com.gift.baseinfo.main.filemanage.FileManage;
import com.gift.baseinfo.main.mapper.FilemanageMapper;
import com.gift.baseinfo.main.service.CommonService;
import com.gift.baseinfo.main.service.FilemanageService;
import com.gift.tools.apis.R;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author liuch
 * @title: CommonServiceImpl
 * @description: TODO
 * @date 2021/10/17 10:45
 */
@Service
public class CommonServiceImpl extends ServiceImpl<FilemanageMapper, FilemanageVo> implements CommonService, FilemanageService {

    @Autowired
    FilemanageMapper filemanageMapper;

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private Map<String, FileManage> fileManageMap;

    @Override
    public R upload(MultipartFile[] files) {
        //获取附件管理实现类
        FileManage fileManage = getFileManage(fileProperties.getModel());
        List<FilemanageVo> infoVos = fileManage.upload(files);
        //保存附件信息到数据库
        this.saveBatch(infoVos);
        //生成预览地址
        for (FilemanageVo infoVo : infoVos) {
            infoVo.setPreviewUrl(fileProperties.getPreviewUrl()+infoVo.getId());
        }
        return R.data(infoVos);
    }

    @Override
    public void previewFile(String id, HttpServletResponse response) {
        //查询id是否存在，并根据存储模式下载附件
        try {
            FilemanageVo filemanage = filemanageMapper.selectById(id);
            if (null != filemanage) {
                String fileModel = filemanage.getModel();
                String datumPath = filemanage.getFilepath();
                String fileType = filemanage.getType();
                FileManage fileManage = getFileManage(fileModel);
                //拼接文件base的data类型
                String file = fileManage.fileDownload(datumPath);
                byte[] image = Base64.decode(file);
                ServletOutputStream out = response.getOutputStream();
                ByteArrayInputStream in = new ByteArrayInputStream(image);
                BufferedImage bufferedImage = ImageIO.read(in);
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                ImageIO.write(bufferedImage, fileType, out);
            } else {
                throw new RuntimeException("下载失败，数据不存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
