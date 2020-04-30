package com.axyy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 家政服务订单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CleanListVo {
    private String b_id;
    private double b_price;
    private String b_icon;
    private String b_title;
    private String state;
    private String b_time;
}
