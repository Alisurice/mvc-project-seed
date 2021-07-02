package com.share.charge.dao;

import com.share.charge.mybatis.generator.model.OmsOrder;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OmsOrderDao {

    @SelectProvider(type = OmsOrderDaoProvider.class, method = "selectByGusetIdSql")
    List<OmsOrder> findOrderByGuestId(Integer guestId);

    @Select("select * from oms_order where guest_id = #{guestId} and return_cabinet_id is null")
    List<OmsOrder> findBorrowByGuestId(Integer guestId);
}
