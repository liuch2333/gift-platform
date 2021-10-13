package com.gift.baseinfo.main.controller;

import com.gift.baseinfo.main.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.convert.Convert;

import com.gift.tools.utils.Condition;
import com.gift.tools.apis.R;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gift.baseinfo.main.entity.SysUser;

/**
 * 系统用户 控制器
 *
 * @author liuch
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/sysUser" )
@Api(value = "系统用户", tags = "系统用户接口" )
public class SysUserController {

    @Autowired
    ISysUserService sysUserService;

    /**
     * 系统用户详情查询
     */
    @GetMapping("/detail" )
    @ApiOperation(value = "系统用户详情查询", notes = "传入 sysUser" )
    public R<SysUser> detail(SysUser sysUser) {
        SysUser detail = sysUserService.getOne(Wrappers.query(sysUser));
        return R.data(detail);
    }


    @GetMapping("/page" )
    @ApiOperation(value = "系统用户分页查询", notes = "传入 sysUser" )
    public R page(Page<SysUser> page, @RequestParam Map<String, Object> params) {
        QueryWrapper<SysUser> wrapper = Condition.getQueryWrapper(params, SysUser.class);
        return R.data(sysUserService.page(page, wrapper));
    }

    @GetMapping("/list" )
    @ApiOperation(value = "系统用户查询", notes = "传入 sysUser" )
    public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<SysUser> wrapper = Condition.getQueryWrapper(params, SysUser.class);
        return R.data(sysUserService.list(wrapper));
    }

    /**
     * 系统用户新增
     */
    @PostMapping("/save" )
    @ApiOperation(value = "系统用户新增", notes = "传入 sysUser" )
    public R save(@Valid @RequestBody SysUser sysUser) {
        return R.status(sysUserService.save(sysUser));
    }

    /**
     * 系统用户 修改
     */
    @PostMapping("/update" )
    @ApiOperation(value = "系统用户修改", notes = "传入 sysUser" )
    public R update(@Valid @RequestBody SysUser sysUser) {
        return R.status(sysUserService.updateById(sysUser));
    }


    /**
     * 系统用户删除
     */
    @PostMapping("/remove" )
    @ApiOperation(value = "系统用户删除", notes = "传入ids" )
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(sysUserService.removeByIds(Convert.toList(Integer.class, ids)));
    }
}
