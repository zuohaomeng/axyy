package com.axyy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestListVo {
    private Long id;

    //投诉编号
    private String s_id;

    private String s_content;

    private String s_reply;

    private String s_state;

    private String s_type;

    private String s_pic;
}
