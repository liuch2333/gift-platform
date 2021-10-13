package com.gift.baseinfo.main.filemanage;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import com.gift.baseinfo.main.conf.FileProperties;
import com.gift.baseinfo.main.entity.FileInfoVo;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuch
 * @title: HdfsFileManage
 * @description: Minio文件管理
 * @date 2021/8/31 14:28
 */
@Service("minioFileManage")
public class MinioFileManage implements FileManage {

    @Autowired
    FileProperties fileProperties;

    private MinioClient minioClient;

    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException {
        if(null == minioClient){
            minioClient = new MinioClient(fileProperties.getMinio_url(), fileProperties.getMinio_appKey(), fileProperties.getMinio_appSecret());
        }
        return minioClient;
    }

    @Override
    public List upload(MultipartFile[] files) {
        List<FileInfoVo> paths = new ArrayList<>();
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        try {
            minioClient = getMinioClient();
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(fileProperties.getMinio_bucket());
            if(!isExist){
                //创建存储桶
                minioClient.makeBucket(fileProperties.getMinio_bucket());
            }
            // 使用putObject上传一个文件到存储桶中。
            for (MultipartFile file : files) {
                FileInfoVo fileInfoVo = new FileInfoVo(file);
                String fullPath = UUID.randomUUID()+"/"+fileInfoVo.getFileName();
                fileInfoVo.setFilePath(fullPath);
                InputStream in = new ByteArrayInputStream(file.getBytes());
                minioClient.putObject(fileProperties.getMinio_bucket(),fullPath,in,new PutObjectOptions(in.available(), -1));
                paths.add(fileInfoVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paths;
    }

    @Override
    public String fileDownload(String path) {
        try {
            minioClient = getMinioClient();
            InputStream inputStream = minioClient.getObject(fileProperties.getMinio_bucket(),path);
            String file = Base64.encode(inputStream);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件下载失败");
        }
    }

    @Override
    public void delFile(String path) {
        try {
            MinioClient minioClient = new MinioClient(fileProperties.getMinio_url(), fileProperties.getMinio_appKey(), fileProperties.getMinio_appSecret());
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(fileProperties.getMinio_bucket());
            if(isExist){
                minioClient.removeObject(fileProperties.getMinio_bucket(),path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
