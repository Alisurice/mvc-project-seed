package com.share.charge.service;

import com.github.pagehelper.PageInfo;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;

public interface AdminService {
    PageInfo<UmsGuest> findAllGuest(int pageNum);
    int deleteGuestById(int id);
    int updateGuestPassword(UmsGuest record);

    PageInfo findAllCharge(Integer pageNum);
    PageInfo findChargeByCabinetId(Integer cabinetId,Integer pageNum);
    int deleteChargeById(int id);

    int updateCharge(PmsCharge charge);

    PmsCharge findChargeById(Integer id);


    int insertCharge(PmsCharge charge);
}
