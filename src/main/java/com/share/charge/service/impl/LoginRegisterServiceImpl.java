package com.share.charge.service.impl;

import com.share.charge.mybatis.generator.mapper.UmsAdminMapper;
import com.share.charge.mybatis.generator.mapper.UmsGuestMapper;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("LoginRegisterService")
public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Override
    public boolean guestLogin(UmsGuest umsGuest) {
        return false;
    }

    @Resource
    private UmsAdminMapper umsAdminMapper;
    @Override
    public boolean adminLogin(UmsAdmin umsAdmin) {

        UmsAdmin temp = umsAdminMapper.login(umsAdmin);
        if(temp==null){
            return false;
        }
        else {
            umsAdmin.setId(temp.getId());
            return true;
        }

    }
}
