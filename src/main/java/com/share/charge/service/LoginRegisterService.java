package com.share.charge.service;

import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;

public interface LoginRegisterService {
    public boolean guestLogin(UmsGuest umsGuest);

    public boolean adminLogin(UmsAdmin umsAdmin);
}
