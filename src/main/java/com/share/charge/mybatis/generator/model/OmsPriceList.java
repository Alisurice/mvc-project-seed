package com.share.charge.mybatis.generator.model;

public class OmsPriceList {
    private Long minTimeSeconds;

    private Long maxTimeSeconds;

    private Double price;

    public Long getMinTimeSeconds() {
        return minTimeSeconds;
    }

    public void setMinTimeSeconds(Long minTimeSeconds) {
        this.minTimeSeconds = minTimeSeconds;
    }

    public Long getMaxTimeSeconds() {
        return maxTimeSeconds;
    }

    public void setMaxTimeSeconds(Long maxTimeSeconds) {
        this.maxTimeSeconds = maxTimeSeconds;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", minTimeSeconds=").append(minTimeSeconds);
        sb.append(", maxTimeSeconds=").append(maxTimeSeconds);
        sb.append(", price=").append(price);
        sb.append("]");
        return sb.toString();
    }
}