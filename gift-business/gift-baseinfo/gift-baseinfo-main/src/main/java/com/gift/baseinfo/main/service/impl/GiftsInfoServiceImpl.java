package com.gift.baseinfo.main.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gift.baseinfo.main.entity.GiftsInfo;
import com.gift.baseinfo.main.mapper.GiftsInfoMapper;
import com.gift.baseinfo.main.service.IGiftsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gift.tools.apis.R;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 礼品信息 服务实现类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Service
public class GiftsInfoServiceImpl extends ServiceImpl<GiftsInfoMapper, GiftsInfo> implements IGiftsInfoService {

    @Autowired
    GiftsInfoMapper giftsInfoMapper;
    @Override
    public R<Page<GiftsInfo>> page(GiftsInfo giftsInfo, int pagesize, int pagenow) {
        Page<GiftsInfo> page = new Page<>(pagenow,pagesize);
        LambdaQueryWrapper<GiftsInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(giftsInfo.getNameEn()),GiftsInfo::getNameEn,giftsInfo.getNameEn())
                .like(StrUtil.isNotBlank(giftsInfo.getNameChs()),GiftsInfo::getNameChs,giftsInfo.getNameChs());
        Page<GiftsInfo> records = this.page(page,wrapper);
        return R.data(records);
    }
}
