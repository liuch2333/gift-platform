package com.gift.baseinfo.main.service;

import com.gift.baseinfo.main.entity.ExchangeRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gift.tools.apis.R;

/**
 * <p>
 * 兑换记录 服务类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
public interface IExchangeRecordService extends IService<ExchangeRecord> {

    R exchangeGift(ExchangeRecord record);

}
