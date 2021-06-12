package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.OmsPriceList;
import com.share.charge.mybatis.generator.model.OmsPriceListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsPriceListMapper {
    long countByExample(OmsPriceListExample example);

    int deleteByExample(OmsPriceListExample example);

    int deleteByPrimaryKey(Long minTimeSeconds);

    int insert(OmsPriceList record);

    int insertSelective(OmsPriceList record);

    List<OmsPriceList> selectByExample(OmsPriceListExample example);

    OmsPriceList selectByPrimaryKey(Long minTimeSeconds);

    int updateByExampleSelective(@Param("record") OmsPriceList record, @Param("example") OmsPriceListExample example);

    int updateByExample(@Param("record") OmsPriceList record, @Param("example") OmsPriceListExample example);

    int updateByPrimaryKeySelective(OmsPriceList record);

    int updateByPrimaryKey(OmsPriceList record);
}