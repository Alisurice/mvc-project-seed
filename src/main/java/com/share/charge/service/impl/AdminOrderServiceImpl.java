package com.share.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.charge.dao.OmsOrderDao;
import com.share.charge.dao.UmsAdminDao;
import com.share.charge.mybatis.generator.mapper.OmsOrderMapper;
import com.share.charge.mybatis.generator.mapper.PmsChargeMapper;
import com.share.charge.mybatis.generator.model.OmsOrder;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.service.AdminOrderService;
import com.share.charge.vo.VoCabinetMes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("AdminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService {

    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

    @Resource
    private OmsOrderDao omsOrderDao;
    @Resource
    private OmsOrderMapper omsOrderMapper;
    @Override
    public PageInfo findOrderByGuestId(Integer guestId, Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<OmsOrder> list = omsOrderDao.findOrderByGuestId(guestId);
        return new PageInfo<OmsOrder>(list,5);
    }

    @Resource
    private PmsChargeMapper pmsChargeMapper;
    //返回order对象以获取订单id
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public OmsOrder createOrder(Integer guestId, Integer chargeId) {
        PmsCharge pmsCharge = pmsChargeMapper.selectByPrimaryKey(chargeId);

        if(pmsCharge==null){
            return null;
        }
        OmsOrder record = new OmsOrder(
                null,guestId,chargeId,
                pmsCharge.getCabinetId(),null,
                new Date().getTime(),null,
                null,200.0,null);

        if(omsOrderMapper.insertSelective(record)==1){
            pmsCharge.setBorrowStatus("借出");
            pmsCharge.setCabinetId(null);
            pmsChargeMapper.updateByPrimaryKey(pmsCharge);
            return record;
        }
        else {
            throw new NullPointerException("fail to create order");
        }
    }

    @Override
    public List<OmsOrder> findBorrowByGuestId(Integer guestId) {
        List<OmsOrder> list = omsOrderDao.findBorrowByGuestId(guestId);
        return list;
    }



    @Override
    public OmsOrder returnCharge(Integer orderId, Integer returnCabinetId) {
        OmsOrder record = new OmsOrder(
                orderId,null,null,
                null, returnCabinetId,
                null, new Date().getTime(),
                null,null,null);

        if(1!=omsOrderMapper.updateByPrimaryKeySelective(record)){
            return null;
        }
        record = omsOrderMapper.selectByPrimaryKey(orderId);
        if(record!=null){
            Long useTime = record.getReturnTimeSeconds()-record.getBorrowTimeSeconds();
            Double bill = 0.0;
            System.out.println(useTime);
            if(useTime<=3600000){
                bill += 10.0;
            }
            else if(useTime>3600000){
                bill +=10.0 +Math.floor(useTime/3600000)*2;
                System.out.println(bill);
                System.out.println(Math.floor(useTime/3600000));
            }

            if(bill>200.0){
                bill = 200.0;
            }
            record.setmBill(bill);
            record.setmBalance(record.getmPrepayment()-bill);
            omsOrderMapper.updateByPrimaryKeySelective(record);

            PmsCharge pmsCharge = new PmsCharge();
            pmsCharge.setBorrowStatus("在柜");
            pmsCharge.setCabinetId(record.getReturnCabinetId());
            pmsCharge.setId(record.getChargeId());
            pmsChargeMapper.updateByPrimaryKeySelective(pmsCharge);
        }
        return record;

    }

    @Override
    public Double returnBalance(Integer orderId) {
        OmsOrder result = omsOrderMapper.selectByPrimaryKey(orderId);
        OmsOrder record = new OmsOrder(
                orderId,null,null,
                null, null,
                null, null,
                null,null,0.0);

        if(1!=omsOrderMapper.updateByPrimaryKeySelective(record)){
            return null;
        }
        return result.getmBalance();
    }
}
