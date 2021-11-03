package com.gift.baseinfo.main.service.impl;

import com.gift.baseinfo.main.entity.Customer;
import com.gift.baseinfo.main.mapper.CustomerMapper;
import com.gift.baseinfo.main.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
