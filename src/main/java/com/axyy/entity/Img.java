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
 * 图片保存地址
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Img implements Serializable {

  private static final long serialVersionUID = 1138110147236302069L;
  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private long id;
  /**
   * 类型
   * 1为动态，2为修理，3为通知公告
   */
  private long type;
  /**
   * 外部连接id
   * 和type一起判断，代表某一类
   */
  private long foreignId;
  /**
   * 地址
   */
  private String url;
  /**
   * 有效位
   * 1.为有效，0为失效
   */
  private long valid;
  /**
   * 创建时间
   */
  private Date createTime;

}
