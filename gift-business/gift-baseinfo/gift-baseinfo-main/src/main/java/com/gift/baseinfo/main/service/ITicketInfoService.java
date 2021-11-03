package com.gift.baseinfo.main.service;

import com.gift.baseinfo.main.entity.TicketInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gift.tools.apis.R;

/**
 * <p>
 * 礼券信息 服务类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
public interface ITicketInfoService extends IService<TicketInfo> {

    /**
     * @author liuch
     * @description 新增礼券信息，生成礼券编号密码
     * @date 2021/10/29 18:30
     * @param
     * @param ticketInfo
     * @return com.gift.tools.apis.R
     */
    R newTicketInfo(TicketInfo ticketInfo);

    R checkTicketInfo(TicketInfo ticketInfo);

}
