package com.share.charge.dao;

import org.apache.ibatis.jdbc.SQL;

public class OmsOrderDaoProvider {
    public String selectByGusetIdSql(final Integer guestId) {

        return new SQL() {
            {
                SELECT("*");
                FROM("oms_order");
                if(guestId != null){
                    WHERE("guest_id = #{guestId}");
                }

            }
        }.toString();
    }
}
