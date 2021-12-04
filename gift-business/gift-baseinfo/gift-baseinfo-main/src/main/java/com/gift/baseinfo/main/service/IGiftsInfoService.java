package com.gift.baseinfo.main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gift.baseinfo.main.entity.GiftsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gift.tools.apis.R;

/**
 * <p>
 * 礼品信息 服务类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
public interface IGiftsInfoService extends IService<GiftsInfo> {

    R<Page<GiftsInfo>> page(GiftsInfo giftsInfo,int pagesize, int pagenow);

}
