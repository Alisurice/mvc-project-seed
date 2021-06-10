package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.OmsOrder;

public interface OmsOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    OmsOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);
}