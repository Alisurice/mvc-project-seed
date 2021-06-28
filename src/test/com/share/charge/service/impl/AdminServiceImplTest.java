package com.share.charge.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageInfo;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdminServiceImplTest {
    @Autowired
    AdminService adminService;

    @Test
    public void findAllGuest() throws JsonProcessingException {
        PageInfo<UmsGuest> pageInfo = adminService.findAllGuest(1);
        for (UmsGuest admin : pageInfo.getList()) {
            System.out.println(admin);
        }


//        test jackson，看看怎么把对象转换成jsonObject以及怎么提取出字段序列
        ObjectMapper objectMapper = new ObjectMapper();
        UmsGuest u = (UmsGuest)pageInfo.getList().get(0);
        String adminJson = objectMapper.writeValueAsString(u);
        JsonNode jsonNode = objectMapper.valueToTree(u);
        ObjectNode objectNode = objectMapper.valueToTree(u);
        Iterator<String> keys = objectNode.fieldNames();
        List<String> keyList = new ArrayList<String>();
        while(keys.hasNext()){
            String key = keys.next();
            keyList.add(key);
            System.out.println("key键是:"+key);
        }
        System.out.println("out:"+ keyList);
    }

    @Test
    public void findChargeByCabinetId() throws JsonProcessingException {
        PageInfo<UmsGuest> pageInfo = adminService.findChargeByCabinetId(1, 1);
        System.out.println(pageInfo.getList());
    }

    @Test
    public void insertCharge() {
        PmsCharge pmsCharge = new PmsCharge();
        pmsCharge.setCabinetId(1);
        pmsCharge.setBorrowStatus("在柜");
        pmsCharge.setPowerLevel(11);
        adminService.insertCharge(pmsCharge);
    }
}