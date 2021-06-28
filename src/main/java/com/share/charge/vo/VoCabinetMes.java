package com.share.charge.vo;

import com.share.charge.mybatis.generator.model.PmsCabinet;
import com.share.charge.mybatis.generator.model.PmsCharge;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class VoCabinetMes extends PmsCabinet {
    int chargeAvailable;
    List<PmsCharge> chargeList;
}
