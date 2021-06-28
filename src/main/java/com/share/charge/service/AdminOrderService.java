package com.share.charge.service;

import com.github.pagehelper.PageInfo;

public interface AdminOrderService {
    PageInfo findOrderByGuestId(Integer guestId, Integer pageNum);
}
