package com.axyy.controller;

import com.axyy.entity.Water;
import com.axyy.service.WaterService;
import com.axyy.util.RequestResult;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2020/4/15--14:22
 */
@Slf4j
@RestController
@RequestMapping("water")
public class WaterController {
    @Resource
    private WaterService waterService;

    @ApiOperation("list")
    @GetMapping("list")
    public Map list(@RequestParam(required = false, defaultValue = "1") int page,
                    @RequestParam(required = false, defaultValue = "10") int size){
        List list = waterService.list(page, size);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", waterService.getCount());
        map.put("data", list);
        return map;
    }

    @ApiOperation("删除")
    @GetMapping("deleteById")
    public RequestResult deleteById(Long id){
        int result = waterService.deleteById(id);
        if(result>0){
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }

    @ApiOperation("搜索")
    @GetMapping("search")
    public Map search(String status,String orderNo){
        List<Water> waters = waterService.search(status, orderNo);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", waters.size());
        map.put("data", waters);
        return map;
    }
    @ApiOperation("下一步")
    @GetMapping("setnext")
    public RequestResult setNext(Long id){
        int result = waterService.setNext(id);
        if(result>0){
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("修改失败");
    }
}
