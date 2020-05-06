package com.axyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 家政服务
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clean implements Serializable {

    private static final long serialVersionUID = -3300177143493366137L;
    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private long id;
    /**
     * 操作用户id
     */
    private long userid;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 类型
     */
    private String type;
    /**
     *  预约时间
     */
    private Date worktime;

    /**
     * 金额
     */
    private double price;
    /**
     * 联系人
     */
    private String username;
    /**
     * 电话
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 状态
     */
    private String status;
    /**
     * 家政电话
     */
    private String cleanphone;
    /**
     * 家政姓名
     */
    private String cleanname;
}
