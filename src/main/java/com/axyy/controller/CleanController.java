package com.axyy.controller;

import com.axyy.entity.Clean;
import com.axyy.service.CleanService;
import com.axyy.util.RequestResult;
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
 * @date 2020/4/15--14:14
 */
@Slf4j
@RestController
@RequestMapping("clean")
public class CleanController {
    @Resource
    private CleanService cleanService;

    @ApiOperation("order")
    @GetMapping("order")
    public RequestResult order(Clean clean) {
        Integer result = cleanService.order(clean);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("预约失败");
    }

    @ApiOperation("list")
    @GetMapping("list")
    public Map list(@RequestParam(required = false, defaultValue = "1") int page,
                    @RequestParam(required = false, defaultValue = "10") int size) {
        List<Clean> list = cleanService.list(page, size);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", cleanService.getCount());
        map.put("data", list);
        return map;
    }
    @ApiOperation("删除")
    @GetMapping("deleteById")
    public RequestResult deleteById(Long id){
        int result = cleanService.deleteById(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }
    @ApiOperation("search")
    @GetMapping("search")
    public Map search(String type,String orderNo){
        List<Clean> cleans = cleanService.search(type, orderNo);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", cleans.size());
        map.put("data", cleans);
        return map;
    }
    @ApiOperation("设置状态")
    @GetMapping("setnext")
    public RequestResult setNext(Long id){
        int result = cleanService.setNext(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("设置失败");
    }
}
