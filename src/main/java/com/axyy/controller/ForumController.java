package com.axyy.controller;

import com.axyy.entity.Forum;
import com.axyy.entity.vo.ForumListwxVO;
import com.axyy.service.ForumService;
import com.axyy.service.DianZanService;
import com.axyy.util.RequestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @date 2020/4/15--14:18
 */
@Slf4j
@RestController
@RequestMapping("forum")
public class ForumController {

    @Resource
    private ForumService forumService;
    @Resource
    private DianZanService dianZanService;
    @ApiOperation("list")
    @GetMapping("list")
    public RequestResult list(@RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "10") int size){
        List list = forumService.list(page, size);
        return RequestResult.SUCCESS(list);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @ApiOperation("插入数据")
    @PostMapping("addByUserId")
    public RequestResult add(@RequestBody String content,@RequestParam String address,@RequestParam long userid) {
        Forum forum = new Forum();
        forum.setUserid(userid);
        forum.setContent(content);
        forum.setAddress(address);
        forum.setCreateTime(new Date());
        Long forumId = forumService.add(forum);
        if (forumId > 0) {
            return RequestResult.SUCCESS("",forumId);
        }
        return RequestResult.ERROR("添加失败");
    }

    @ApiOperation("点赞")
    @GetMapping("like")
    public RequestResult like(long userid,long forumid){
        log.info("userid={},forumid={}",userid,forumid);
        dianZanService.like(userid,forumid);
        return RequestResult.SUCCESS();
    }

    @ApiOperation("微信动态集合")
    @GetMapping("wxlist")
    public List<ForumListwxVO> wxlist(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size){
        //获取相应数量的动态
        List<ForumListwxVO> list = forumService.wxlist(page, size);
        System.out.println(1);
        return list;
    }

}
