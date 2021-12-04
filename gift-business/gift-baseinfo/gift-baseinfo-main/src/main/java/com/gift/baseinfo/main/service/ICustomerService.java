package com.gift.baseinfo.main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gift.baseinfo.main.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gift.tools.apis.R;

/**
 * <p>
 * 客户信息 服务类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
public interface ICustomerService extends IService<Customer> {

    R<Page<Customer>> page(Customer customer, int pagesize, int pagenow);

}
