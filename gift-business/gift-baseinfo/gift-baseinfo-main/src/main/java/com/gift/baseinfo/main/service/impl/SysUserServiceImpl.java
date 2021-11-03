package com.gift.baseinfo.main.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gift.baseinfo.main.entity.SysUser;
import com.gift.baseinfo.main.mapper.SysUserMapper;
import com.gift.baseinfo.main.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gift.tools.apis.R;
import com.gift.tools.utils.TextUtils;
import com.mysql.cj.util.TestUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    /**
     * @author liuch
     * @description 系统登录
     * @date 2021/10/30 9:58
     * @param
     * @param sysUser
     * @return com.gift.tools.apis.R<com.gift.baseinfo.main.entity.SysUser>
     */
    @Override
    public R<SysUser> login(SysUser sysUser) {
        //系统登录，密码采用MD5+随机salt
        String userPwd = sysUser.getPassword();
        //根基用户名查询
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserno,sysUser.getUserno());
        sysUser = baseMapper.selectOne(queryWrapper);
        if(null != sysUser){
            String md5Pwd = sysUser.getPassword();
            boolean verResult = TextUtils.verify(userPwd,md5Pwd);
            if(verResult){
                return R.data(sysUser);
            }else {
                return R.fail("秘密错误!");
            }
        }else {
            return R.fail("用户不存在！");
        }
    }

    /**
     * @author liuch
     * @description 新增用户
     * @date 2021/10/30 9:58
     * @param
     * @param sysUser
     * @return com.gift.tools.apis.R<com.gift.baseinfo.main.entity.SysUser>
     */
    @Override
    public R<SysUser> newSysUser(SysUser sysUser) {
        //判断用户名是否存在
        if (exists(sysUser.getUserno()).isSuccess()){
            //判断是否有密码,没有则默认登录名+123
            if(StrUtil.isBlank(sysUser.getPassword())){
                sysUser.setPassword(sysUser.getUserno()+"123");
            }
            //密码MD5+salt加密
            String md5Pwd = TextUtils.generate(sysUser.getPassword());
            sysUser.setPassword(md5Pwd);
            this.save(sysUser);
            return R.data(sysUser);
        }else {
            return R.fail("用户名已存在");
        }
    }

    @Override
    public R exists(String userNo) {
        //根基用户名查询
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserno,userNo);
        int count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            return R.fail("用户名已存在");
        }else {
            return R.ok();

        }
    }
}
