package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.OmsPriceList;

public interface OmsPriceListMapper {
    int deleteByPrimaryKey(Long minTimeSeconds);

    int insert(OmsPriceList record);

    int insertSelective(OmsPriceList record);

    OmsPriceList selectByPrimaryKey(Long minTimeSeconds);

    int updateByPrimaryKeySelective(OmsPriceList record);

    int updateByPrimaryKey(OmsPriceList record);
}