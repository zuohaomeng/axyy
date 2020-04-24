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
 * 维修服务
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Repair implements Serializable {

  private static final long serialVersionUID = -4976697933264647441L;
  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private long id;
  /**
   * 用户id
   */
  private long userid;
  /**
   * 修理编号
   */
  private String repairNo;
  /**
   * 类型
   */
  private String type;
  /**
   * 内容
   */
  private String content;
  /**
   * 区域
   */
  private String area;
  /**
   * 地址
   */
  private String address;
  /**
   * 联系人
   */
  private String username;
  /**
   * 联系电话
   */
  private String phone;
  /**
   * 创建时间
   */
  private Date createtime;
  /**
   * 状态
   */
  private String status;




}
