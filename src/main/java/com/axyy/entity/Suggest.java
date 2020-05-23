package com.axyy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 投诉建议
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Suggest {
  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private long id;

  public String suggestNo;
  /**
   * 用户id
   */
  private long userid;
  /**
   * 内容
   */
  private String content;
  /**
   * 类型
   */
  private String type;
  /**
   * 创建时间
   */
    private Date createDate;
  /**
   * 状态
   */
  private String status;
  /**
   * 回复
   */
  private String reply;


}
