package com.gift.baseinfo.main.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 发票信息
 * </p>
 *
 * @author liuch
 * @since 2021-11-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="BillInfo对象", description="发票信息")
public class BillInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "客户id")
      private Integer custId;

      @ApiModelProperty(value = "单位名称")
      private String companyName;

      @ApiModelProperty(value = "税号")
      private String billCode;

      @ApiModelProperty(value = "开户行")
      private String billBanka;

      @ApiModelProperty(value = "公司地址")
      private String compAddress;

      @ApiModelProperty(value = "公司电话")
      private String compTel;

      @ApiModelProperty(value = "申请时间")
      private LocalDateTime applyTime;

      @ApiModelProperty(value = "0 未开 1 已开")
      private Integer state;

      @ApiModelProperty(value = "电子发票地址")
      private String elecBillUrl;

      @ApiModelProperty(value = "邮箱")
      private String billEmail;


}
