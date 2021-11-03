package com.gift.baseinfo.main.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 礼券信息
 * </p>
 *
 * @author liuch
 * @since 2021-10-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="TicketInfo对象", description="礼券信息")
public class TicketInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "礼券id")
        private Integer id;

      @ApiModelProperty(value = "客户id")
      private Integer custId;

      @ApiModelProperty(value = "礼券编号")
      private String ticketNo;

      @ApiModelProperty(value = "礼券密码")
      private String ticketPwd;

      @ApiModelProperty(value = "礼券面额(元)")
      private BigDecimal ticketValue;

      @ApiModelProperty(value = "礼券余额(元)")
      private BigDecimal ticketSurplus;

      @ApiModelProperty(value = "添加时间")
      private LocalDateTime createtime;


}
