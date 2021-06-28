package com.share.charge.service;

import com.github.pagehelper.PageInfo;
import com.share.charge.mybatis.generator.model.PmsCabinet;
import com.share.charge.vo.VoCabinetMes;

public interface AdminCabinetService {
    PageInfo findCabinetByPosition(String position, Integer pageNum);
    int insertCabinet(PmsCabinet pmsCabinet);

    PmsCabinet findCabinetById(Integer id);

    VoCabinetMes findVoCabinetById(Integer id);
}
