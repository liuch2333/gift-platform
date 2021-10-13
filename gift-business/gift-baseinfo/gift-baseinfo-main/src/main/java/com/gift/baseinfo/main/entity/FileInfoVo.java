package com.gift.baseinfo.main.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuch
 * @title: FileInfoVo
 * @description: 文件信息
 * @date 2021/8/31 16:50
 */
@Data
public class FileInfoVo {
    /**
     * @Author liuch
     * @Description 文件路径
     * @Date 2021/8/31 16:52
     * @param
     * @return
     */
    private String filePath;
    /**
     * @Author liuch
     * @Description 文件名
     * @Date 2021/8/31 16:52
     * @param
     * @return
     */
    private String fileName;
    /**
     * @Author liuch
     * @Description 文件大小
     * @Date 2021/8/31 16:52
     * @param
     * @return
     */
    private Long size;
    /**
     * @Author liuch
     * @Description 文件类型
     * @Date 2021/8/31 16:52
     * @param
     * @return
     */
    private String type;

    /**
     * @Author liuch
     * @Description 文件内容
     * @Date 2021/8/31 17:02
     * @param
     * @return
     */

    public FileInfoVo(MultipartFile file) {
        this.fileName = file.getOriginalFilename();
        this.size = file.getSize();
        this.type = this.fileName.substring(this.fileName.lastIndexOf(".")+1);
    }
}
