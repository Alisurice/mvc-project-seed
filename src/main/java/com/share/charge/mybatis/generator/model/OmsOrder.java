package com.share.charge.mybatis.generator.model;

public class OmsOrder {
    private Integer id;

    private Integer guestId;

    private Integer chargeId;

    private Integer borrowCabinetId;

    private Integer returnCabinetId;

    private Long borrowTimeSeconds;

    private Long returnTimeSeconds;

    private Double mBill;

    private Double mPrepayment;

    private Double mBalance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getBorrowCabinetId() {
        return borrowCabinetId;
    }

    public void setBorrowCabinetId(Integer borrowCabinetId) {
        this.borrowCabinetId = borrowCabinetId;
    }

    public Integer getReturnCabinetId() {
        return returnCabinetId;
    }

    public void setReturnCabinetId(Integer returnCabinetId) {
        this.returnCabinetId = returnCabinetId;
    }

    public Long getBorrowTimeSeconds() {
        return borrowTimeSeconds;
    }

    public void setBorrowTimeSeconds(Long borrowTimeSeconds) {
        this.borrowTimeSeconds = borrowTimeSeconds;
    }

    public Long getReturnTimeSeconds() {
        return returnTimeSeconds;
    }

    public void setReturnTimeSeconds(Long returnTimeSeconds) {
        this.returnTimeSeconds = returnTimeSeconds;
    }

    public Double getmBill() {
        return mBill;
    }

    public void setmBill(Double mBill) {
        this.mBill = mBill;
    }

    public Double getmPrepayment() {
        return mPrepayment;
    }

    public void setmPrepayment(Double mPrepayment) {
        this.mPrepayment = mPrepayment;
    }

    public Double getmBalance() {
        return mBalance;
    }

    public void setmBalance(Double mBalance) {
        this.mBalance = mBalance;
    }
}