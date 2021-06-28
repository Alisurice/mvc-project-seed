package com.share.charge.dao;

import com.share.charge.mybatis.generator.model.UmsGuest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UmsGuestDao {
    @Select("select * from ums_guest")
    List<UmsGuest> findAll();

    @Select("select username from ums_guest where username = #{username}")
    UmsGuest hasUser(@Param("username") String username);
}
