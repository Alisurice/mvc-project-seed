package com.share.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.charge.dao.OmsOrderDao;
import com.share.charge.dao.UmsAdminDao;
import com.share.charge.mybatis.generator.model.OmsOrder;
import com.share.charge.service.AdminOrderService;
import com.share.charge.vo.VoCabinetMes;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AdminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService {

    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

    @Resource
    private OmsOrderDao omsOrderDao;
    @Override
    public PageInfo findOrderByGuestId(Integer guestId, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<OmsOrder> list = omsOrderDao.findOrderByGuestId(guestId);
        return new PageInfo<OmsOrder>(list,5);
    }
}
