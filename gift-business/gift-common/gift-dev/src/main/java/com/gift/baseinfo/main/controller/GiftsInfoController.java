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
import com.gift.baseinfo.main.entity.GiftsInfo;
import com.gift.baseinfo.main.service.GiftsInfoService;

/**
 * 礼品信息 控制器
 *
 * @author liuch
 * @since 2021-10-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/giftsInfo")
@Api(value = "礼品信息", tags = "礼品信息接口")
public class GiftsInfoController {

private final GiftsInfoService giftsInfoService;

/**
 * 礼品信息详情查询
 */
@GetMapping("/detail")
@ApiOperation(value = "礼品信息详情查询", notes = "传入 giftsInfo")
public R<GiftsInfo> detail(GiftsInfo giftsInfo) {
    GiftsInfo detail = giftsInfoService.getOne(Wrappers.query(giftsInfo));
        return R.data(detail);
        }


@GetMapping("/page")
@ApiOperation(value = "礼品信息分页查询", notes = "传入 giftsInfo")
public R page(Page<GiftsInfo> page,@RequestParam Map<String, Object> params) {
        QueryWrapper<GiftsInfo> wrapper =  Condition.getQueryWrapper(params,GiftsInfo.class);
        return R.data(giftsInfoService.page(page,wrapper));
        }

@GetMapping("/list")
@ApiOperation(value = "礼品信息查询", notes = "传入 giftsInfo")
public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<GiftsInfo> wrapper =  Condition.getQueryWrapper(params,GiftsInfo.class);
        return R.data(giftsInfoService.list(wrapper));
        }

/**
 *  礼品信息新增
 */
@PostMapping("/save")
@ApiOperation(value = "礼品信息新增", notes = "传入 giftsInfo")
public R save(@Valid @RequestBody GiftsInfo giftsInfo) {
        return R.status(giftsInfoService.save(giftsInfo));
        }

/**
 * 礼品信息 修改
 */
@PostMapping("/update")
@ApiOperation(value = "礼品信息修改", notes = "传入 giftsInfo")
public R update(@Valid @RequestBody GiftsInfo giftsInfo) {
        return R.status(giftsInfoService.updateById(giftsInfo));
        }


/**
 *  礼品信息删除
 */
@PostMapping("/remove")
@ApiOperation(value = "礼品信息删除", notes = "传入ids")
public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(giftsInfoService.removeByIds(Convert.toList(Integer.class,ids)));
        }
        }