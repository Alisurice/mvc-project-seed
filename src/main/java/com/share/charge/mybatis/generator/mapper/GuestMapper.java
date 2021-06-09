package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.Guest;

public interface GuestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Guest record);

    int insertSelective(Guest record);

    Guest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Guest record);

    int updateByPrimaryKey(Guest record);
}