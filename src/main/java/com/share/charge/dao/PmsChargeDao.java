package com.share.charge.dao;

import com.github.pagehelper.PageInfo;
import com.share.charge.mybatis.generator.model.PmsCharge;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PmsChargeDao {

    @Select("select * from pms_charge")
    List<com.share.charge.mybatis.generator.model.PmsCharge> findAllCharge();

    @Select("select * from pms_charge where cabinet_id = #{cabinetId} and borrow_status='在柜'")
    List<com.share.charge.mybatis.generator.model.PmsCharge> findChargeByCabinetId(Integer cabinetId);

    @Delete("delete from pms_charge where id = #{id,jdbcType=INTEGER}")
    int deleteChargeById(@Param("id") Integer id);

    @UpdateProvider(type = UmsAdminDaoProvider.class, method = "updateChargeSql")
    int updateCharge(com.share.charge.mybatis.generator.model.PmsCharge charge);

    @Select("select count(id) from pms_charge " +
            "where cabinet_id = #{cabinetId} and borrow_status='在柜'")
    int chargeInCabinet(Integer cabinetId);


}
