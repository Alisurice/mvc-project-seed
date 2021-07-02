package com.share.charge.service.impl;

import com.share.charge.service.AdminOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.crypto.Data;
import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdminOrderServiceImplTest {
    @Autowired
    AdminOrderService adminOrderService;
    @Test
    public void test(){
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());

        Long lo = date.getTime();
        date.setTime(lo);
        System.out.println(date);

        Date date2 = new Date(lo);
        System.out.println(date2);

    }

    @Test
    public void findOrderByGuestId() {
    }

    @Test
    public void createOrder() {
    }

    @Test
    public void findBorrowByGuestId() {
        System.out.println(adminOrderService.findBorrowByGuestId(2));
    }

    @Test
    public void returnCharge() {

        System.out.println(adminOrderService.returnCharge(10,2));
    }
}