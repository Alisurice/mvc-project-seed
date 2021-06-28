package com.share.charge.dao;

import com.share.charge.mybatis.generator.model.OmsOrder;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface OmsOrderDao {

    @SelectProvider(type = OmsOrderDaoProvider.class, method = "selectByGusetIdSql")
    List<OmsOrder> findOrderByGuestId(Integer guestId);
}
