package com.share.charge.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.charge.dao.UmsGuestDao;
import com.share.charge.mybatis.generator.mapper.UmsGuestMapper;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LoginRegisterServiceImplTest {
    @Autowired
    private LoginRegisterServiceImpl loginRegisterService;

    @Test
    public void adminLogin() {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("1");
        umsAdmin.setPassword("1");
        Assert.assertEquals(true, loginRegisterService.adminLogin(umsAdmin));

    }


    @Resource
    private UmsGuestDao umsGuestDao;
    @Test
    public void findAllpage(){
        PageHelper.startPage(1,2);
        List<UmsGuest> list = umsGuestDao.findAll();
        Page<UmsGuest> page = (Page)list;
        PageInfo<UmsGuest> pageInfo = new PageInfo<>(list);
        for(UmsGuest u : list){
            System.out.println(u);
        }
        if(list !=null ){
            System.out.println(page.get(0));
        }

    }

    @Test
    public void adminRegister() {
        UmsAdmin umsAdmin = new UmsAdmin();

        umsAdmin.setUsername("abcd");
        System.out.println(loginRegisterService.adminRegister(umsAdmin).getMessage());

        umsAdmin.setPassword("abcd");
        System.out.println(loginRegisterService.adminRegister(umsAdmin).getMessage());
    }
}