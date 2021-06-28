package com.share.charge.dao;

import com.share.charge.mybatis.generator.model.PmsCabinet;
import com.share.charge.vo.VoCabinetMes;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PmsCabinetDao {

    @SelectProvider(type = PmsCabinetDaoProvider.class, method = "selectCabinetByPositionSql")
    List<PmsCabinet> findCabinetByPosition(String position);



    List<VoCabinetMes> findByPosition(String position);


    @Select("select * from pms_cabinet where position like #{position}")
    @Results(id = "Message" ,value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "chargeAvailable",column = "id",javaType = Integer.class,
            one = @One(select = "com.share.charge.dao.PmsChargeDao.chargeInCabinet"))
    })
    List<VoCabinetMes> testFind(String position);


    @Select("select * from pms_cabinet where id=#{id}")
    @Results(id = "VoCabinetMes" ,value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "chargeList",column = "id",
                    many = @Many(select = "com.share.charge.dao.PmsChargeDao.findChargeByCabinetId"))
    })
    VoCabinetMes findVoCabinetById(Integer id);
}
