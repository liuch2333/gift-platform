package com.gift.baseinfo.main.entity;

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
 * 客户信息
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("customer")
@ApiModel(value="Customer对象", description="客户信息")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "客户姓名")
    private String custName;

    @ApiModelProperty(value = "客户电话")
    private String custTel;

    @ApiModelProperty(value = "客户性别 0女 1男")
    private Integer custSex;

    @ApiModelProperty(value = "客户年龄")
    private Integer custAge;

    @ApiModelProperty(value = "信息来源")
    private String custSource;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "登记时间")
    private LocalDateTime createtime;


}
