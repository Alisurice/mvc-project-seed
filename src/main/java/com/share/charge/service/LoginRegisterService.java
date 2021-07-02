package com.share.charge.service;

import com.share.charge.dto.LoginRegisterDTO;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;

public interface LoginRegisterService {
    public boolean guestLogin(UmsGuest umsGuest);

    public boolean adminLogin(UmsAdmin umsAdmin);

    LoginRegisterDTO adminRegister(UmsAdmin umsAdmin);

    LoginRegisterDTO guestRegister(UmsGuest umsGuest);
}
