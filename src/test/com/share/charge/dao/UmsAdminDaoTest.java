package com.share.charge.dao;

import com.share.charge.mybatis.generator.mapper.PmsChargeMapper;
import com.share.charge.mybatis.generator.mapper.UmsGuestMapper;
import com.share.charge.mybatis.generator.model.PmsCharge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UmsAdminDaoTest {
    @Resource
    private UmsAdminDao umsAdminDao;
    @Resource
    PmsChargeMapper pmsChargeMapper;
    @Resource
    PmsChargeDao pmsChargeDao;
    @Test
    public void findAllCharge() {

        System.out.println(pmsChargeDao.findAllCharge());
        System.out.println(pmsChargeMapper.selectByPrimaryKey(1));

        System.out.println(pmsChargeDao.findChargeByCabinetId(1));
    }

    @Test
    public void updateCharge() {
        //id 必须在pms_charge中，不然也返回0
        PmsCharge pmsCharge = new PmsCharge(4,1,1,"test");
        pmsChargeDao.updateCharge(pmsCharge);
        System.out.println(pmsChargeDao.updateCharge(pmsCharge));
    }
}