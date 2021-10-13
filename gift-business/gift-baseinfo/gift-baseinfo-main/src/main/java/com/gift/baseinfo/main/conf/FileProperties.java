package com.gift.baseinfo.main.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author liuch
 * @title: FileProperties
 * @description: 附件管理配置类
 * @date 2021/8/31 15:36
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /**
     * @Author liuch
     * @Description 文件上传模式   local  oss fastDfs  hdfs minio....
     * @Date 2021/8/31 15:49
     * @param
     * @return
     */
    @Value("${file.model}")
    String model = "local";

    /**
     * @author liuch
     * @description 文件存储路径
     * @date 2021/9/13 18:08
     * @param
     * @param null
     * @return
     */
    @Value("${file.basepath}")
    String basepath = "D:\\hdfiles\\";


    /**
     * @author liuch
     * @description minio服务地址
     * @date 2021/9/13 18:09
     * @param
     * @param null
     * @return
     */
    @Value("${file.minio.url}")
    String minio_url = "https://play.min.io";

    /**
     * @author liuch
     * @description minio认证key
     * @date 2021/9/13 18:09
     * @param
     * @param null
     * @return
     */
    @Value("${file.minio.appKey}")
    String minio_appKey = "Q3AM3UQ867SPQQA43P2F";

    /**
     * @author liuch
     * @description minio认证密钥
     * @date 2021/9/13 18:09
     * @param
     * @param null
     * @return
     */
    @Value("${file.minio.appSecret}")
    String minio_appSecret = "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG";

    /**
     * @author liuch
     * @description monio文件存储桶
     * @date 2021/9/13 18:09
     * @param
     * @param null
     * @return
     */
    @Value("${file.minio.bucket}")
    String minio_bucket = "hd-basic";



}
