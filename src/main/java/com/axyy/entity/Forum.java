package com.axyy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forum implements Serializable {

  /**
   * 自增id
   */
  @TableId(type= IdType.AUTO)
  private long id;
  /**
   * 用户
   */
  private long userid;
  /**
   * 内容
   */
  private String content;
  /**
   * 创建时间
   */
  private Date createTime;

  private String address;


}
