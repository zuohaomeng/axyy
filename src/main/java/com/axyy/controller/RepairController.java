package com.axyy.controller;

import com.axyy.entity.Notice;
import com.axyy.entity.Repair;
import com.axyy.service.RepairService;
import com.axyy.util.RequestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2020/4/15--14:19
 */
@Slf4j
@RestController
@RequestMapping("repair")
public class RepairController {

    @Resource
    private RepairService repairService;

    @ApiOperation("list")
    @GetMapping("list")
    public Map<String, Object> list(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size) {
        List list = repairService.list(page, size);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", repairService.getCount());
        map.put("data", list);
        return map;
    }



    @ApiOperation("批量删除")
    @PostMapping("deleteList")
    public RequestResult deleteList(@RequestBody List<Repair> repairs) {
        int result = repairService.deleteList(repairs);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }

    @ApiOperation("删除")
    @RequestMapping("deleteById")
    public RequestResult deleteById(int id) {
        int result = repairService.deleteById(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }

    @ApiOperation("搜索")
    @RequestMapping("search")
    public Map search(String status, String repairNo) {
        List<Repair> repairs = repairService.search(status, repairNo);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", map.size());
        map.put("data", repairs);
        return map;
    }

    @ApiOperation("调整状态")
    @RequestMapping("setnext")
    public RequestResult setNext(Long id) {
        int result = repairService.setNext(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("修改失败");
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @ApiOperation("插入数据")
    @PostMapping("addByUserId")
    public RequestResult add(@RequestBody String content,@RequestParam long userid) {
        Repair repair = new Repair();
        repair.setContent(content);
        repair.setUserid(userid);
        Long repairId = repairService.add(repair);
        if (repairId > 0) {
            return RequestResult.SUCCESS("",repairId);
        }
        return RequestResult.ERROR("添加失败");
    }


}
