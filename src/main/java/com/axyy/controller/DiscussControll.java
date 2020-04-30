package com.axyy.controller;

import com.axyy.entity.Discuss;
import com.axyy.entity.User;
import com.axyy.service.DiscussService;
import com.axyy.service.UserService;
import com.axyy.util.RequestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论
 */
@Slf4j
@RestController
@RequestMapping("discuss")
public class DiscussControll {
    @Resource
    private DiscussService discussService;
    @Resource
    private UserService userService;


    @ApiOperation("添加评论")
    @GetMapping("add")
    public RequestResult add(long forumId,long userid,String content){
        User user = userService.getById(userid);
        Discuss discuss = Discuss.builder()
                .content(content)
                .userid(userid)
                .forumId(forumId)
                .username(user.getName())
                .build();
        int result = discussService.add(discuss);
        if(result > 0){
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("添加失败");
    }
}
