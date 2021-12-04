package com.gift.baseinfo.main.filemanage;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import com.gift.baseinfo.main.conf.FileProperties;
import com.gift.baseinfo.main.entity.FilemanageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuch
 * @title: LocalFileManage
 * @description: 本地磁盘文件管理
 * @date 2021/8/31 14:25
 */
@Service("localFileManage")
public class LocalFileManage implements FileManage {

    @Autowired
    FileProperties fileProperties;

    @Override
    public List upload(MultipartFile[] files) {

        List<FilemanageVo> paths = new ArrayList<>();
        try {
            LocalDate now = LocalDate.now();
            String date = now.getYear()+"/"+now.getMonthValue()+"/"+now.getDayOfMonth()+"/"+ System.currentTimeMillis()+"/";
            String path = fileProperties.getBasepath()+date;
            for (MultipartFile file : files) {
                FilemanageVo fileInfoVo = new FilemanageVo();
                fileInfoVo.setFilemanageVo(file);
                long timestamp = System.currentTimeMillis();
                String fullPath = path+timestamp+"-"+fileInfoVo.getFilename();
                fileInfoVo.setFilepath(fullPath);
                byte[] content = file.getBytes();
                FileUtil.writeBytes(content,fullPath);
                paths.add(fileInfoVo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    @Override
    public String fileDownload(String path) {
        //查看本地是否存在文件
        if(FileUtil.exist(path)){
            byte[] bytes = FileUtil.readBytes(path);
            String file = Base64.encode(bytes);
            return file;
        }else {
            throw new RuntimeException("文件不存在!");
        }
    }

    @Override
    public void delFile(String path) {
        //查看本地是否存在文件
        if(FileUtil.exist(path)){
            FileUtil.del(path);
        }else {
            throw new RuntimeException("文件不存在!");
        }
    }
}
