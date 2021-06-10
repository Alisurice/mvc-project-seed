package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.PmsCharge;

public interface PmsChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmsCharge record);

    int insertSelective(PmsCharge record);

    PmsCharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmsCharge record);

    int updateByPrimaryKey(PmsCharge record);
}