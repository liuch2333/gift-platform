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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 礼券信息
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ticket_info")
@ApiModel(value="TicketInfo对象", description="礼券信息")
public class TicketInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "礼券id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户id")
    private Integer custId;

    @ApiModelProperty(value = "礼券编号")
    private String ticketNo;

    @ApiModelProperty(value = "礼券密码")
    private String ticketPwd;

    @ApiModelProperty(value = "礼券面额(元)")
    @DecimalMin(value = "1",message = "礼券面额最低不能低于1元")
    private BigDecimal ticketValue;

    @ApiModelProperty(value = "礼券余额(元)")
    private BigDecimal ticketSurplus;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createtime;


    @ApiModelProperty(value = "礼品卡状态 0 有效 1 已兑换")
    private Integer state;
}
