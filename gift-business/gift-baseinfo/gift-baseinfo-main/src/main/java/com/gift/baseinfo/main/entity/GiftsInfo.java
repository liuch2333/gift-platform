package com.gift.baseinfo.main.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 礼品信息
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gifts_info")
@ApiModel(value="GiftsInfo对象", description="礼品信息")
public class GiftsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "礼品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "礼品编号")
    private String giftNo;

    @ApiModelProperty(value = "库存")
    private Integer giftCount;

    @ApiModelProperty(value = "礼品名称-中文名")
    private String nameChs;

    @ApiModelProperty(value = "礼品名称-英文")
    private String nameEn;

    @ApiModelProperty(value = "礼品价值")
    private BigDecimal giftValue;

    @ApiModelProperty(value = "礼品规格")
    private String giftSpecs;

    @ApiModelProperty(value = "礼品详情")
    private String giftDetail;

    @ApiModelProperty(value = "提醒")
    private String giftTips;

    @ApiModelProperty(value = "礼品轮播图片")
    private String giftImages;

    @ApiModelProperty(value = "礼品预览图")
    private String giftPreimg;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;




}
