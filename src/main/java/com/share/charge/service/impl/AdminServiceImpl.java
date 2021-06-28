package com.share.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.charge.controller.admin.ChargeController;
import com.share.charge.dao.PmsCabinetDao;
import com.share.charge.dao.PmsChargeDao;
import com.share.charge.dao.UmsAdminDao;
import com.share.charge.mybatis.generator.mapper.PmsCabinetMapper;
import com.share.charge.mybatis.generator.mapper.PmsChargeMapper;
import com.share.charge.mybatis.generator.mapper.UmsAdminMapper;
import com.share.charge.mybatis.generator.model.PmsCabinet;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

    @Resource
    private UmsAdminDao umsAdminDao;
    @Resource
    private PmsChargeDao pmsChargeDao;

    @Resource
    private PmsCabinetMapper pmsCabinetMapper;

    @Override
    public PageInfo<UmsGuest> findAllGuest(int pageNum){
        PageHelper.startPage(pageNum, 5);
        List<UmsGuest> list = umsAdminDao.findAll();
        return new PageInfo<UmsGuest>(list,5);
    }

    public int deleteGuestById(int id){
        return umsAdminDao.deleteById(id);
    }
    public int updateGuestPassword(UmsGuest record){
        if(record.getPassword()==null||record.getPassword().isEmpty()){
            return 0;
        }
        return umsAdminDao.updateGuestPassword(record);
    }

    public PageInfo findAllCharge(Integer pageNum){

        PageHelper.startPage(pageNum, 5);
        List<PmsCharge> list = pmsChargeDao.findAllCharge();
        LOGGER.debug("findAllCharge:"+list.toString());
        return new PageInfo<PmsCharge>(list,5);
    }
    public PageInfo findChargeByCabinetId(Integer cabinetId,Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<PmsCharge> list = pmsChargeDao.findChargeByCabinetId(cabinetId);
//        LOGGER.debug("findAllCharge:"+list.toString());
        return new PageInfo<>(list, 5);
    }
    public int deleteChargeById(int id){
        return pmsChargeDao.deleteChargeById(id);
    }

    public int updateCharge(PmsCharge charge){
        return pmsChargeDao.updateCharge(charge);
    }


    @Resource
    private PmsChargeMapper pmsChargeMapper;
    public PmsCharge findChargeById(Integer id){
        return pmsChargeMapper.selectByPrimaryKey(id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public int insertCharge(PmsCharge charge) {
        int count = pmsChargeDao.chargeInCabinet(charge.getCabinetId());
        PmsCabinet pmsCabinet = pmsCabinetMapper.selectByPrimaryKey(charge.getCabinetId());
        if(count >= pmsCabinet.getMaxCharge()){
            return -1;
        }
        else {
            int result = pmsChargeMapper.insert(charge);
//            int i = 1 / 0;
            return result;
        }

    }


}
