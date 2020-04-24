package com.axyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private long id;
  /**
   * 用户名
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 类型
   */
  private long type;
  /**
   * 性别
   */
  private String sex;
  /**
   * 姓名
   */
  private String name;
  /**
   * 创建时间
   */
  private Date createtime;
  /**
   * 电话
   */
  private String phone;
  /**
   * 身份证
   */
  private String idcard;
  /**
   * 楼宇
   */
  private String building;
  /**
   * 单元
   */
  private String unit;
  /**
   * 房间号
   */
  private String apartment;
  /**
   * uuid
   */
  private String openid;


}
