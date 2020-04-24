package com.axyy.controller;

import com.axyy.entity.OpenIdJson;
import com.axyy.entity.User;
import com.axyy.entity.Water;
import com.axyy.service.UserService;
import com.axyy.util.HttpUtil;
import com.axyy.util.RequestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2020/4/15--14:20
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("管理员登陆")
    @PostMapping("login")
    public RequestResult login(@RequestBody User user) {
        Integer login = userService.login(user);
        if (login <= 0) {
            return RequestResult.ERROR("账号或者密码错误");
        }
        return RequestResult.SUCCESS("登录成功");
    }

    /**
     * date = 1，  跳转填写信息页面
     * date = 2，  说明有此用户
     *
     * @param openid
     * @return
     */
    @ApiOperation("下一步操作")
    @GetMapping("getNextStep")
    public RequestResult getNextStep(String openid) {
        User user = userService.getUserByOpenid(openid);
        if (user == null) {
            return RequestResult.SUCCESS("无此用户", 1);
        }
        return RequestResult.SUCCESS(2);
    }

    private String appID = "wx4d62ec3bbeac93d3";
    private String appSecret = "e4984a0fb79159adddbc99863370de4b";


    @ApiOperation("获取openid")
    @PostMapping("getOpenId")
    public RequestResult getOpenid(@RequestParam("code") String code) throws JsonProcessingException {
        String result = "";
        try {
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + this.appID + "&secret="
                            + this.appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result, OpenIdJson.class);
        log.info("result={}", result.toString());

        return RequestResult.SUCCESS("", openIdJson.getOpenid());
    }

    @ApiOperation("获取用户资料")
    @GetMapping("getUser")
    public RequestResult getUser(String openid) {
        User user = userService.getUserByOpenid(openid);
        if (user == null) {
            return RequestResult.ERROR("无此用户");
        }
        return RequestResult.SUCCESS(user);
    }


    @ApiOperation("保存用户信息")
    @PostMapping("saveUserInfo")
    public RequestResult saveUserInfo(@RequestBody User user,
                                      @RequestParam("openid") String openid,
                                      @RequestParam("roomid") String roomid) {
        if(!"axyy".equals(roomid)){
            return RequestResult.ERROR("roomid错误");
        }
        user.setOpenid(openid);
        int result = userService.saveUserByOpenId(user);
        if (result > 0) {
            return RequestResult.SUCCESS("保存成功！");
        }
        return RequestResult.ERROR("保存失败！");
    }

//----------------------------------
    @ApiOperation("list")
    @GetMapping("list")
    public Map list(@RequestParam(required = false, defaultValue = "1") int page,
                    @RequestParam(required = false, defaultValue = "10") int size) {
        List<User> users = userService.list(page,size);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", userService.getCount());
        map.put("data", users);
        return map;
    }

    @ApiOperation("删除")
    @GetMapping("deleteById")
    public RequestResult deleteById(Long id){
        int result = userService.deleteById(id);
        if(result>0){
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }
    @ApiOperation("搜索")
    @GetMapping("search")
    public Map search(String name){
        List<User> waters = userService.search(name);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", waters.size());
        map.put("data", waters);
        return map;
    }

    @ApiOperation("添加")
    @GetMapping("add")
    public RequestResult add(@RequestBody User user){
        return  RequestResult.SUCCESS();
    }
}
