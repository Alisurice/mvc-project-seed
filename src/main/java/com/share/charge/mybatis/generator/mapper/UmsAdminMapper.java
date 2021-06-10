package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.UmsAdmin;

public interface UmsAdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    UmsAdmin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UmsAdmin record);

    int updateByPrimaryKey(UmsAdmin record);
}