package com.axyy.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 家政服务
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CleanAddVo {
    private String name;
    private String phone;
    private long userid;
    private String type;
    private String worktime;
    private double price;
    private String address;
}
