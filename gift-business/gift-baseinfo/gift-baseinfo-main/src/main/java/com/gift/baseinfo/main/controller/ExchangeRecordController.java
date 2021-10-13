package com.gift.baseinfo.main.controller;

import com.gift.baseinfo.main.service.IExchangeRecordService;
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
import com.gift.baseinfo.main.entity.ExchangeRecord;

/**
 * 兑换记录 控制器
 *
 * @author liuch
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/exchangeRecord" )
@Api(value = "兑换记录", tags = "兑换记录接口" )
public class ExchangeRecordController {

    @Autowired
    IExchangeRecordService exchangeRecordService;

    /**
     * 兑换记录详情查询
     */
    @GetMapping("/detail" )
    @ApiOperation(value = "兑换记录详情查询", notes = "传入 exchangeRecord" )
    public R<ExchangeRecord> detail(ExchangeRecord exchangeRecord) {
        ExchangeRecord detail = exchangeRecordService.getOne(Wrappers.query(exchangeRecord));
        return R.data(detail);
    }


    @GetMapping("/page" )
    @ApiOperation(value = "兑换记录分页查询", notes = "传入 exchangeRecord" )
    public R page(Page<ExchangeRecord> page, @RequestParam Map<String, Object> params) {
        QueryWrapper<ExchangeRecord> wrapper = Condition.getQueryWrapper(params, ExchangeRecord.class);
        return R.data(exchangeRecordService.page(page, wrapper));
    }

    @GetMapping("/list" )
    @ApiOperation(value = "兑换记录查询", notes = "传入 exchangeRecord" )
    public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<ExchangeRecord> wrapper = Condition.getQueryWrapper(params, ExchangeRecord.class);
        return R.data(exchangeRecordService.list(wrapper));
    }

    /**
     * 兑换记录新增
     */
    @PostMapping("/save" )
    @ApiOperation(value = "兑换记录新增", notes = "传入 exchangeRecord" )
    public R save(@Valid @RequestBody ExchangeRecord exchangeRecord) {
        return R.status(exchangeRecordService.save(exchangeRecord));
    }

    /**
     * 兑换记录 修改
     */
    @PostMapping("/update" )
    @ApiOperation(value = "兑换记录修改", notes = "传入 exchangeRecord" )
    public R update(@Valid @RequestBody ExchangeRecord exchangeRecord) {
        return R.status(exchangeRecordService.updateById(exchangeRecord));
    }


    /**
     * 兑换记录删除
     */
    @PostMapping("/remove" )
    @ApiOperation(value = "兑换记录删除", notes = "传入ids" )
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(exchangeRecordService.removeByIds(Convert.toList(Integer.class, ids)));
    }
}
