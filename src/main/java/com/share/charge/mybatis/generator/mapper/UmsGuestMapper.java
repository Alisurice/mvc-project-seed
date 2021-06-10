package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.UmsGuest;

public interface UmsGuestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UmsGuest record);

    int insertSelective(UmsGuest record);

    UmsGuest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UmsGuest record);

    int updateByPrimaryKey(UmsGuest record);
}