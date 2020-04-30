package com.axyy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("dianzan")
public class DianZan {
    /**
     * 自增id
     */
    @TableId(type= IdType.AUTO)
    private Long id;

    /**
     * 评论id
     */
    private Long forumid;
    /**
     * 点喜欢人的名字
     */
    private String likename;
    /**
     * 点喜欢人的userid
     */
    private Long likeuserid;
}
