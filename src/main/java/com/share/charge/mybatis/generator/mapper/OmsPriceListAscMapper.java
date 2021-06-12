package com.share.charge.mybatis.generator.mapper;

import com.share.charge.mybatis.generator.model.OmsPriceListAsc;
import com.share.charge.mybatis.generator.model.OmsPriceListAscExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsPriceListAscMapper {
    long countByExample(OmsPriceListAscExample example);

    int deleteByExample(OmsPriceListAscExample example);

    int insert(OmsPriceListAsc record);

    int insertSelective(OmsPriceListAsc record);

    List<OmsPriceListAsc> selectByExample(OmsPriceListAscExample example);

    int updateByExampleSelective(@Param("record") OmsPriceListAsc record, @Param("example") OmsPriceListAscExample example);

    int updateByExample(@Param("record") OmsPriceListAsc record, @Param("example") OmsPriceListAscExample example);
}