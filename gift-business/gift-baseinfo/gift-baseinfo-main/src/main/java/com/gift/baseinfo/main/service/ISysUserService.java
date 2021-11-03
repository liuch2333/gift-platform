package com.gift.baseinfo.main.service;

import com.gift.baseinfo.main.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gift.tools.apis.R;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
public interface ISysUserService extends IService<SysUser> {

    R<SysUser> login(SysUser sysUser);

    R<SysUser> newSysUser(SysUser sysUser);

    R exists(String userNo);
}
