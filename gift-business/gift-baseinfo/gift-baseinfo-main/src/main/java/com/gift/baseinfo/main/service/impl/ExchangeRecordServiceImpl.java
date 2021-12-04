package com.gift.baseinfo.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gift.baseinfo.main.entity.ExchangeRecord;
import com.gift.baseinfo.main.entity.GiftsInfo;
import com.gift.baseinfo.main.entity.TicketInfo;
import com.gift.baseinfo.main.mapper.ExchangeRecordMapper;
import com.gift.baseinfo.main.mapper.GiftsInfoMapper;
import com.gift.baseinfo.main.mapper.TicketInfoMapper;
import com.gift.baseinfo.main.service.IExchangeRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gift.tools.apis.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 兑换记录 服务实现类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Service
public class ExchangeRecordServiceImpl extends ServiceImpl<ExchangeRecordMapper, ExchangeRecord> implements IExchangeRecordService {

    @Autowired
    TicketInfoMapper ticketInfoMapper;
    @Autowired
    GiftsInfoMapper giftsInfoMapper;
    /**
     * @author liuch
     * @description 礼品兑换
     * @date 2021/11/3 8:56
     * @param
     * @param record
     * @return com.gift.tools.apis.R
     */
    @Override
    public R exchangeGift(ExchangeRecord record) {
        //查询礼品卡是否存在
        LambdaQueryWrapper<TicketInfo> ticketWrapper = new LambdaQueryWrapper<>();
        ticketWrapper.eq(TicketInfo::getTicketNo,record.getTicketNo());
        TicketInfo ticketInfo =ticketInfoMapper.selectOne(ticketWrapper);
        if(null == ticketInfo){
            return R.fail("礼品卡不存在");
        }
        //验证礼品是否存在
        if(null != record.getGoodId()){
            LambdaQueryWrapper<GiftsInfo> giftWrapper = new LambdaQueryWrapper<>();
            giftWrapper.eq(GiftsInfo::getId,record.getGoodId());
            GiftsInfo giftsInfo =giftsInfoMapper.selectOne(giftWrapper);
            if(null == giftsInfo){
                return R.fail("礼品不存在");
            }else if(ticketInfo.getTicketSurplus().compareTo(giftsInfo.getGiftValue()) == 1){
                //验证库存是否充足 去除库存
                /*if (giftsInfo.getGiftCount().compareTo(0) == 0){
                    return R.fail("礼品库存不足！");
                }*/
                //验证礼品卡余额是否充足
                //写入兑换记录，并扣除余额
                record.setCreatetime(LocalDateTime.now());
                if(this.save(record)){
                    //设置礼品卡状态为已兑换
                    ticketInfo.setState(1);
                    ticketInfoMapper.updateById(ticketInfo);
                    /*
                    //库存减1
                    giftsInfo.setGiftCount(giftsInfo.getGiftCount()-1);
                    giftsInfoMapper.updateById(giftsInfo);*/
                    return R.ok();
                }else {
                    return R.fail("兑换失败，请联系客服人员处理！");
                }
            }else{
                return R.fail("兑换失败，礼品卡当前余额为"+ticketInfo.getTicketSurplus()+",元，不能兑换价值高于余额的礼品！");
            }
        }else {
            return R.fail("请选择要兑换的礼品");
        }
    }
}
