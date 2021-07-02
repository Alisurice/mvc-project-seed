package com.share.charge.vo;

import lombok.Data;

import java.util.Date;

@Data
public class VoCreateOrder {
    Boolean success;
    int orderId;
    Date createTime;
    String message;
}
