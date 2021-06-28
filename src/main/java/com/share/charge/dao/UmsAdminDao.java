package com.share.charge.dao;

import com.share.charge.mybatis.generator.model.PmsCharge;
import com.share.charge.mybatis.generator.model.UmsAdmin;
import com.share.charge.mybatis.generator.model.UmsGuest;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UmsAdminDao {
    @Select("select * from ums_guest")
    List<UmsGuest> findAll();

    @Select("select username from ums_guest where username = #{username}")
    UmsAdmin findUser(@Param("username") String username);
    @Delete("delete from ums_guest where id = #{id,jdbcType=INTEGER}")
    int deleteById(@Param("id") Integer id);
    
    @Update("update ums_guest " +
            "set password = #{password,jdbcType=VARCHAR} " +
            "where id = #{id,jdbcType=INTEGER} ")
    int updateGuestPassword(UmsGuest record);



}
