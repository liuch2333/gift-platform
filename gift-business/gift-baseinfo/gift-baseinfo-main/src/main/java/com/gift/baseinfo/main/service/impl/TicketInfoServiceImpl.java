package com.gift.baseinfo.main.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gift.baseinfo.main.entity.TicketInfo;
import com.gift.baseinfo.main.mapper.TicketInfoMapper;
import com.gift.baseinfo.main.service.ITicketInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gift.tools.apis.R;
import com.gift.tools.utils.TextUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 礼券信息 服务实现类
 * </p>
 *
 * @author liuch
 * @since 2021-10-10
 */
@Service
public class TicketInfoServiceImpl extends ServiceImpl<TicketInfoMapper, TicketInfo> implements ITicketInfoService {


    /**
     * @author liuch
     * @description 新增礼券信息，生成礼券编号密码
     * @date 2021/10/29 18:30
     * @param
     * @param ticketInfo
     * @return com.gift.tools.apis.R
     */
    @Override
    public R newTicketInfo(TicketInfo ticketInfo) {
        if(null == ticketInfo.getCustId() || null ==ticketInfo.getTicketValue()){
            return R.fail("客户id和礼券金额不能为空！");
        }
        //todo 验证客户id是否存在
        ticketInfo.setCreatetime(LocalDateTime.now());
        //生成卡号 暂定：六位客户id+随机5个字符+时间戳后6位
        String timestamp = String.valueOf(System.currentTimeMillis());
        String ticketNo = String.format("%06d", ticketInfo.getCustId())+"-"+TextUtils.randomText(5)+"-"+timestamp.substring(timestamp.length()-6);
        //生成密码 暂定：随机8个字符
        String ticketPwd = TextUtils.randomText(8);
        //密码加密
        String md5Pwd = TextUtils.generate(ticketPwd);
        ticketInfo.setTicketNo(ticketNo);
        ticketInfo.setTicketSurplus(ticketInfo.getTicketValue());
        ticketInfo.setTicketPwd(md5Pwd);
        if(this.save(ticketInfo)){
            ticketInfo.setTicketPwd(ticketPwd);
            return R.data(ticketInfo);
        }else{
            return R.fail("添加失败");
        }

    }

    @Override
    public R checkTicketInfo(TicketInfo ticketInfo) {
        //获取当前密码
        String ticketPwd = ticketInfo.getTicketPwd();
        //查询礼券卡号是否存在
        LambdaQueryWrapper<TicketInfo> ticketWrapper = new LambdaQueryWrapper<>();
        ticketWrapper.eq(TicketInfo::getTicketNo,ticketInfo.getTicketNo());
        ticketInfo = baseMapper.selectOne(ticketWrapper);
        if(null !=ticketInfo){
            boolean isExists = TextUtils.verify(ticketPwd,ticketInfo.getTicketPwd());
            if(isExists){
                return R.data(ticketInfo);
            }else{
                return R.fail("密码错误！");
            }
        }else {
            return R.fail("礼品卡不存在!");
        }
    }
}
