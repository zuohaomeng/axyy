package com.axyy.controller;

import com.axyy.entity.Discuss;
import com.axyy.service.DiscussService;
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
 * @date 2020/4/15--14:16
 */
@Slf4j
@RestController
@RequestMapping("discuss")
public class DiscussControll {
    @Resource
    private DiscussService discussService;


    @ApiOperation("getfromforum")
    @GetMapping("getfromforum")
    public RequestResult getFromForum(Long forumId){
        List fromForum = discussService.getFromForum(forumId);
        return RequestResult.SUCCESS(fromForum);
    }

    @ApiOperation("add")
    @PostMapping("add")
    public RequestResult add(Discuss discuss){
        int result = discussService.add(discuss);
        if(result > 0){
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("添加失败");
    }
}
