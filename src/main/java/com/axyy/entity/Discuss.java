package com.axyy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 评论
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discuss implements Serializable {


  private static final long serialVersionUID = -1879540057203737139L;
  /**
   * 自增id
   */
  @TableId(type = IdType.AUTO)
  private long id;
  /**
   * 动态id
   */
  private long forumId;
  /**
   * 用户id
   */
  private long userid;
  /**
   * 评论人名称
   */
  private String username;
  /**
   * 内容
   */
  private String content;


}
