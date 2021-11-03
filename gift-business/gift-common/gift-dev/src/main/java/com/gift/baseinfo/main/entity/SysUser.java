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
 * 系统用户
 * </p>
 *
 * @author liuch
 * @since 2021-10-17
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysUser对象", description="系统用户")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "登录名/编号")
      private String userno;

      @ApiModelProperty(value = "用户名")
      private String username;

      @ApiModelProperty(value = "密码")
      private String password;

      @ApiModelProperty(value = "手机号")
      private Integer tel;

      @ApiModelProperty(value = "创建时间")
      private LocalDateTime createtime;


}
