package com.gift.baseinfo.main.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gift.baseinfo.main.entity.Customer;
import com.gift.baseinfo.main.mapper.CustomerMapper;
import com.gift.baseinfo.main.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gift.tools.apis.R;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息 服务实现类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public R<Page<Customer>> page(Customer customer, int pagesize, int pagenow) {
        Page<Customer> page = new Page<>(pagenow,pagesize);
        LambdaQueryWrapper<Customer> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(customer.getCustName()),Customer::getCustName,customer.getCustName())
                .like(StrUtil.isNotBlank(customer.getCustTel()),Customer::getCustTel,customer.getCustTel());
        Page<Customer> records = this.page(page,wrapper);
        return R.data(records);
    }
}
