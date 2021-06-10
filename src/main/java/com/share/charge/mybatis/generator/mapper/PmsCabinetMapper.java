package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.PmsCabinet;

public interface PmsCabinetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmsCabinet record);

    int insertSelective(PmsCabinet record);

    PmsCabinet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmsCabinet record);

    int updateByPrimaryKey(PmsCabinet record);
}