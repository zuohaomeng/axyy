package com.axyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Water {

  /**
   * 自增id
   */
  private long id;
  /**
   * 用户id
   */
  private long userid;
  /**
   * 订单id
   */
  private String orderNo;
  /**
   * 内容
   */
  private String content;
  /**
   * 价格
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
   * 创建时间
   */
  private Date createtime;
  /**
   * 备注
   */
  private String note;



}
