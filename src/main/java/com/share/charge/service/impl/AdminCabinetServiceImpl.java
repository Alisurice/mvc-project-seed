package com.share.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.share.charge.dao.PmsCabinetDao;
import com.share.charge.dao.PmsChargeDao;
import com.share.charge.mybatis.generator.mapper.PmsCabinetMapper;
import com.share.charge.mybatis.generator.model.PmsCabinet;
import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.service.AdminCabinetService;
import com.share.charge.vo.VoCabinetMes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("AdminCabinetService")
public class AdminCabinetServiceImpl implements AdminCabinetService {

    @Resource
    private PmsCabinetDao pmsCabinetDao;
    @Resource
    private PmsCabinetMapper pmsCabinetMapper;

//    public PageInfo findCabinetByPosition(String position, Integer pageNum){
//        PageHelper.startPage(pageNum, 5);
//        List<PmsCabinet> list = pmsCabinetDao.findCabinetByPosition("%"+position+"%");
//        return new PageInfo<PmsCabinet>(list,5);
//    }
    @Override
    public PageInfo findCabinetByPosition(String position, Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<VoCabinetMes> list = pmsCabinetDao.findByPosition("%"+position+"%");
        return new PageInfo<VoCabinetMes>(list,5);
    }

    @Override
    public int insertCabinet(PmsCabinet pmsCabinet){
        return pmsCabinetMapper.insert(pmsCabinet);
    }

    @Override
    public PmsCabinet findCabinetById(Integer id) {
        return pmsCabinetMapper.selectByPrimaryKey(id);
    }

    @Override
    public VoCabinetMes findVoCabinetById(Integer id) {
        return pmsCabinetDao.findVoCabinetById(id);
    }


}
