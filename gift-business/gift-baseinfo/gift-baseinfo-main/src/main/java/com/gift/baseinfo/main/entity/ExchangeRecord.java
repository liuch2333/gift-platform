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

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 兑换记录
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exchange_record")
@ApiModel(value="ExchangeRecord对象", description="兑换记录")
public class ExchangeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "兑换记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "礼品id")
    @Min(value = 0,message = "未关联礼品信息")
    private Integer goodId;

    @ApiModelProperty(value = "礼券编号")
    @NotBlank(message = "礼品卡号不能为空")
    private String ticketNo;

    @ApiModelProperty(value = "礼品价值")
    @DecimalMin(value = "1",message = "礼品价值最低不能低于1元")
    private BigDecimal giftValue;

    @ApiModelProperty(value = "兑换人")
    @NotBlank()
    private String username;

    @ApiModelProperty(value = "兑换人电话")
    private String tel;

    @ApiModelProperty(value = "送货地址")
    private String address;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "兑换时间")
    private LocalDateTime createtime;


}
