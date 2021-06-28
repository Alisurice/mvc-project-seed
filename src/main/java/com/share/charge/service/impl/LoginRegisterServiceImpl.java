package com.share.charge.service.impl;

import com.share.charge.dao.UmsAdminDao;
import com.share.charge.dto.LoginRegisterDTO;
import com.share.charge.mybatis.generator.mapper.UmsAdminMapper;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.LoginRegisterService;
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
    @Resource
    private UmsAdminDao umsAdminDao;
    @Override
    public LoginRegisterDTO adminRegister(UmsAdmin umsAdmin) {
        System.out.println("adminRegister:"+umsAdmin.toString());
        if(umsAdmin.getUsername().isEmpty() || umsAdmin.getPassword().isEmpty()){
            return new LoginRegisterDTO(false,"用户名和密码不能为空");
        }
        else if(umsAdminDao.findUser(umsAdmin.getUsername()) == null){
            umsAdminMapper.insert(umsAdmin);
            return new LoginRegisterDTO(true,"注册成功");
        }
        else {
            return new LoginRegisterDTO(false,"用户名已经存在");
        }

    }
}
