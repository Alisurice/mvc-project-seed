package com.share.charge.service;

import com.github.pagehelper.PageInfo;
import com.share.charge.mybatis.generator.model.OmsOrder;

import java.util.List;

public interface AdminOrderService {
    PageInfo findOrderByGuestId(Integer guestId, Integer pageNum);

    OmsOrder createOrder(Integer id, Integer chargeId);

    List<OmsOrder> findBorrowByGuestId(Integer guestId);

    OmsOrder returnCharge(Integer orderId,Integer cabinetId);

    Double returnBalance(Integer orderId);
}
