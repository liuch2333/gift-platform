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
 * 兑换记录
 * </p>
 *
 * @author liuch
 * @since 2021-10-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="ExchangeRecord对象", description="兑换记录")
public class ExchangeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "兑换记录id")
        private Integer id;

      @ApiModelProperty(value = "礼品id")
      private Integer goodId;

      @ApiModelProperty(value = "礼券编号")
      private Integer ticketNo;

      @ApiModelProperty(value = "礼品价值")
      private BigDecimal giftValue;

      @ApiModelProperty(value = "兑换人")
      private String username;

      @ApiModelProperty(value = "兑换人电话")
      private Integer tel;

      @ApiModelProperty(value = "送货地址")
      private String address;

      @ApiModelProperty(value = "备注")
      private String remark;

      @ApiModelProperty(value = "兑换时间")
      private LocalDateTime createtime;


}
