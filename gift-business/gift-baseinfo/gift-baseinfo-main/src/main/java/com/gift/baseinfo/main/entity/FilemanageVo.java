package com.gift.baseinfo.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author liuch
 * @since 2021-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Filemanage对象", description = "")
@TableName(value = "fileManage")
public class FilemanageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件路径")
    @TableField("filePath")
    private String filepath;

    @ApiModelProperty(value = "文件名")
    @TableField("fileName")
    private String filename;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "存储模式")
    private String model;

    @ApiModelProperty(value = "上传时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预览地址")
    @TableField(exist = false)
    private String previewUrl;

    /**
     * @param
     * @return
     * @Author liuch
     * @Description 文件内容
     * @Date 2021/8/31 17:02
     */

    public void setFilemanageVo(MultipartFile file) {
        this.filename = file.getOriginalFilename();
        this.size = file.getSize();
        this.type = this.filename.substring(this.filename.lastIndexOf(".") + 1);
    }



}
