package com.share.charge.service.impl;

import com.share.charge.mybatis.generator.model.UmsAdmin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
}