package com.share.charge.dao;

import com.share.charge.controller.admin.ChargeController;
import com.share.charge.mybatis.generator.model.PmsCharge;
import org.apache.ibatis.jdbc.SQL;
import org.apache.log4j.Logger;

import java.util.Set;

public class UmsAdminDaoProvider {
    private static final Logger LOGGER = Logger.getLogger(UmsAdminDaoProvider.class);
    public String updateChargeSql(final PmsCharge charge) {
        return new SQL() {
            {
                String updateCondition = "";
                UPDATE("pms_charge");
                if(charge.getCabinetId()!=null) {
                    SET("cabinet_id = #{cabinetId}");
                    updateCondition = " #{cabinetId} in (select cabinet_id from pms_cabinet)";
                }
                if(charge.getBorrowStatus()!=null) {
                    SET("borrow_status = #{borrowStatus,jdbcType=VARCHAR}");
                }
                if(charge.getPowerLevel()!=null) {
                    SET("power_level = #{powerLevel,jdbcType=INTEGER}");
                }
                WHERE("id=#{id}",updateCondition);
            }
        }.toString();
    }
}
