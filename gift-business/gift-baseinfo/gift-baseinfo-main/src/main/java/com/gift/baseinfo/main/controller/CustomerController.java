package com.gift.baseinfo.main.controller;

import com.gift.baseinfo.main.entity.GiftsInfo;
import com.gift.baseinfo.main.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import com.gift.baseinfo.main.entity.Customer;

/**
 * 客户信息 控制器
 *
 * @author liuch
 * @since 2021-10-10
 */
@RestController
@RequestMapping("/customer" )
@Api(value = "客户信息", tags = "客户信息接口" )
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    /**
     * 客户信息详情查询
     */
    @GetMapping("/detail" )
    @ApiOperation(value = "客户信息详情查询", notes = "传入 customer" )
    public R<Customer> detail(Customer customer) {
        Customer detail = customerService.getOne(Wrappers.query(customer));
        return R.data(detail);
    }


    @PostMapping("/page" )
    @ApiOperation(value = "客户信息分页查询", notes = "传入 customer" )
    public R page(@RequestBody Customer customer, @RequestParam("pagesize") @ApiParam(value = "分页条数") int pagesize, @RequestParam("pagenow") @ApiParam(value = "当前页数")  int pagenow) {
        return customerService.page(customer,pagesize, pagenow);
    }

    @GetMapping("/list" )
    @ApiOperation(value = "客户信息查询", notes = "传入 customer" )
    public R list(@RequestParam Map<String, Object> params) {
        QueryWrapper<Customer> wrapper = Condition.getQueryWrapper(params, Customer.class);
        return R.data(customerService.list(wrapper));
    }

    /**
     * 客户信息新增
     */
    @PostMapping("/save" )
    @ApiOperation(value = "客户信息新增", notes = "传入 customer" )
    public R save(@Valid @RequestBody Customer customer) {
        customerService.save(customer);
        return R.data(customer);
    }

    /**
     * 客户信息 修改
     */
    @PostMapping("/update" )
    @ApiOperation(value = "客户信息修改", notes = "传入 customer" )
    public R update(@Valid @RequestBody Customer customer) {
        customerService.updateById(customer);
        return R.data(customer);
    }


    /**
     * 客户信息删除
     */
    @PostMapping("/remove/id" )
    @ApiOperation(value = "客户信息删除", notes = "传入id" )
    public R remove(@ApiParam(value = "主键集合", required = true) @PathVariable("id") String id) {
        return R.status(customerService.removeById(id));
    }
}
