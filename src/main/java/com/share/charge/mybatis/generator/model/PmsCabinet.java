package com.share.charge.mybatis.generator.model;

public class PmsCabinet {
    private Integer id;

    private String position;

    private Integer maxCharge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getMaxCharge() {
        return maxCharge;
    }

    public void setMaxCharge(Integer maxCharge) {
        this.maxCharge = maxCharge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", position=").append(position);
        sb.append(", maxCharge=").append(maxCharge);
        sb.append("]");
        return sb.toString();
    }
}