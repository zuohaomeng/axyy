package com.axyy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信保修列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepairListVo {
    //订单号
    private String r_id;
    //
    private String submitTime;
    private String repairContext;
    private int r_state;
    private String state;
    private String r_icon;
    /**
     * 修理人
     */
    private String repairName;
    /**
     * 修理电话
     */
    private String repairPhone;
}
