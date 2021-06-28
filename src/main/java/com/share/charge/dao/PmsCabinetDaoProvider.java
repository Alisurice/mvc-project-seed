package com.share.charge.dao;

import com.share.charge.mybatis.generator.model.PmsCharge;
import org.apache.ibatis.jdbc.SQL;

public class PmsCabinetDaoProvider {
    public String selectCabinetByPositionSql(String position) {

        return new SQL() {
            {
                SELECT("*");
                FROM("pms_cabinet");
                WHERE("position like #{position}");
            }
        }.toString();
    }
}
