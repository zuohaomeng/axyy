package com.axyy.controller;

import com.axyy.service.ForumService;
import com.axyy.util.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/4/15--14:18
 */
@Slf4j
@RestController
@RequestMapping("forum")
public class ForumController {

    @Resource
    private ForumService forumService;


    @ApiOperation("list")
    @GetMapping("list")
    public RequestResult list(@RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "10") int size){
        List list = forumService.list(page, size);
        return RequestResult.SUCCESS(list);
    }
}
