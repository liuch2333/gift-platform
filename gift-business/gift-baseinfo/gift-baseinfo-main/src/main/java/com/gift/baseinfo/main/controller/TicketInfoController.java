package com.gift.baseinfo.main.controller;

import com.gift.baseinfo.main.service.ITicketInfoService;
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
import com.gift.baseinfo.main.entity.TicketInfo;

/**
 * 礼券信息 控制器
 *
 * @author liuch
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/ticketInfo" )
@Api(value = "礼券信息", tags = "礼券信息接口" )
public class TicketInfoController {

    @Autowired
    ITicketInfoService ticketInfoService;

    /**
     * 礼券信息详情查询
     */
    @GetMapping("/detail" )
    @ApiOperation(value = "礼券信息详情查询", notes = "传入 ticketInfo" )
    public R<TicketInfo> detail(TicketInfo ticketInfo) {
        TicketInfo detail = ticketInfoService.getOne(Wrappers.query(ticketInfo));
        return R.data(detail);
    }


    @GetMapping("/page" )
    @ApiOperation(value = "礼券信息分页查询", notes = "传入 ticketInfo" )
    public R page(Page<TicketInfo> page, @RequestParam Map<String, Object> params) {
        QueryWrapper<TicketInfo> wrapper = Condition.getQueryWrapper(params, TicketInfo.class);
        return R.data(ticketInfoService.page(page, wrapper));
    }

    @GetMapping("/list" )
    @ApiOperation(value = "礼券信息查询", notes = "传入 ticketInfo" )
    public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<TicketInfo> wrapper = Condition.getQueryWrapper(params, TicketInfo.class);
        return R.data(ticketInfoService.list(wrapper));
    }

    /**
     * 礼券信息新增
     */
    @PostMapping("/save" )
    @ApiOperation(value = "礼券信息新增", notes = "传入 ticketInfo" )
    public R save(@Valid @RequestBody TicketInfo ticketInfo) {
        return ticketInfoService.newTicketInfo(ticketInfo);
    }

    /**
     * 礼券验证-兑换礼品
     */
    @PostMapping("/checkTicketInfo" )
    @ApiOperation(value = "礼券信息新增", notes = "传入 ticketInfo" )
    public R checkTicketInfo(@RequestBody TicketInfo ticketInfo) {
        return ticketInfoService.checkTicketInfo(ticketInfo);
    }

    /**
     * 礼券信息 修改
     */
    @PostMapping("/update" )
    @ApiOperation(value = "礼券信息修改", notes = "传入 ticketInfo" )
    public R update(@Valid @RequestBody TicketInfo ticketInfo) {
        return R.status(ticketInfoService.updateById(ticketInfo));
    }


    /**
     * 礼券信息删除
     */
    @PostMapping("/remove/{id}" )
    @ApiOperation(value = "礼券信息删除", notes = "传入ids" )
    public R remove(@ApiParam(value = "主键", required = true) @PathVariable("id") String id) {
        return R.status(ticketInfoService.removeById(id));
    }


}
