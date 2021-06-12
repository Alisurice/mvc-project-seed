package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.UmsGuest;
import com.share.charge.mybatis.generator.model.UmsGuestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsGuestMapper {
    long countByExample(UmsGuestExample example);

    int deleteByExample(UmsGuestExample example);

    int deleteByPrimaryKey(@Param("id") Integer id, @Param("username") String username);

    int insert(UmsGuest record);

    int insertSelective(UmsGuest record);

    List<UmsGuest> selectByExample(UmsGuestExample example);

    UmsGuest selectByPrimaryKey(@Param("id") Integer id, @Param("username") String username);

    int updateByExampleSelective(@Param("record") UmsGuest record, @Param("example") UmsGuestExample example);

    int updateByExample(@Param("record") UmsGuest record, @Param("example") UmsGuestExample example);

    int updateByPrimaryKeySelective(UmsGuest record);

    int updateByPrimaryKey(UmsGuest record);
}