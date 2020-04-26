package com.axyy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeVo  {
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
    private String createDate;
    /**
     * 状态
     */
    private String status;
}
