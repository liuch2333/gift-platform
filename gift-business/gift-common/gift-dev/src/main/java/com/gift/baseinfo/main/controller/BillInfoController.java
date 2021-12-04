package com.gift.baseinfo.main.controller;

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
import org.springframework.web.bind.annotation.*;
import com.gift.baseinfo.main.entity.BillInfo;
import com.gift.baseinfo.main.service.BillInfoService;

/**
 * 发票信息 控制器
 *
 * @author liuch
 * @since 2021-11-21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/billInfo")
@Api(value = "发票信息", tags = "发票信息接口")
public class BillInfoController {

private final BillInfoService billInfoService;

/**
 * 发票信息详情查询
 */
@GetMapping("/detail")
@ApiOperation(value = "发票信息详情查询", notes = "传入 billInfo")
public R<BillInfo> detail(BillInfo billInfo) {
    BillInfo detail = billInfoService.getOne(Wrappers.query(billInfo));
        return R.data(detail);
        }


@GetMapping("/page")
@ApiOperation(value = "发票信息分页查询", notes = "传入 billInfo")
public R page(Page<BillInfo> page,@RequestParam Map<String, Object> params) {
        QueryWrapper<BillInfo> wrapper =  Condition.getQueryWrapper(params,BillInfo.class);
        return R.data(billInfoService.page(page,wrapper));
        }

@GetMapping("/list")
@ApiOperation(value = "发票信息查询", notes = "传入 billInfo")
public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<BillInfo> wrapper =  Condition.getQueryWrapper(params,BillInfo.class);
        return R.data(billInfoService.list(wrapper));
        }

/**
 *  发票信息新增
 */
@PostMapping("/save")
@ApiOperation(value = "发票信息新增", notes = "传入 billInfo")
public R save(@Valid @RequestBody BillInfo billInfo) {
        return R.status(billInfoService.save(billInfo));
        }

/**
 * 发票信息 修改
 */
@PostMapping("/update")
@ApiOperation(value = "发票信息修改", notes = "传入 billInfo")
public R update(@Valid @RequestBody BillInfo billInfo) {
        return R.status(billInfoService.updateById(billInfo));
        }


/**
 *  发票信息删除
 */
@PostMapping("/remove")
@ApiOperation(value = "发票信息删除", notes = "传入ids")
public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(billInfoService.removeByIds(Convert.toList(Integer.class,ids)));
        }
        }
