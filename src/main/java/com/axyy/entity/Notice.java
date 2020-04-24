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
 * 通知公告
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice implements Serializable {
  private static final long serialVersionUID = -5795230493794177196L;
  @TableId(type = IdType.AUTO)
  private long id;
  /**
   * 内容
   */
  private String content;
  /**
   * 图片
   */
  private String imgurl;
  /**
   * 类型
   */
  private String type;
  /**
   * 标题
   */
  private String title;
  /**
   * 创建时间
   */
  private Date createDate;
  /**
   * 状态
   */
  private String status;
}
