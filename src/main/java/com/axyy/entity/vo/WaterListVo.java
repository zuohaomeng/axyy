package com.axyy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaterListVo {
    private String w_id;
    private double w_price;
    private String w_icon;
    private String w_title;
    private String w_time;
    private String w_note;
    private String state;
    private int w_state;
}
