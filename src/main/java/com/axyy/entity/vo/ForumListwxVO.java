package com.axyy.entity.vo;

import com.axyy.entity.DianZan;
import com.axyy.entity.Discuss;
import com.axyy.entity.Img;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForumListwxVO {

    //id
    private long id;
    //头像地址
    private String headimgurl;
    //
    private Long userid;
    //昵称
    private String nickname;
    //内容
    private String content;
    //图片
    private List<Img> img;
    private String address;
    private String time;
    //点赞人姓名
    private List<String> dianzan;
    //评论
    private List<Discuss> pinglun;

}
