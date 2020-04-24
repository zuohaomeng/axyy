package com.axyy.controller;

import com.axyy.entity.Notice;
import com.axyy.entity.Repair;
import com.axyy.entity.Suggest;
import com.axyy.service.SuggestService;
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
 * @date 2020/4/15--14:20
 */
@Slf4j
@RestController
@RequestMapping("suggest")
public class SuggestController {

    @Resource
    private SuggestService suggestService;

    @ApiOperation("list")
    @GetMapping("list")
    public Map list(@RequestParam(required = false, defaultValue = "1") int page,
                    @RequestParam(required = false, defaultValue = "10") int size){
        List list = suggestService.list(page, size);
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", suggestService.getCount());
        map.put("data", list);
        return map;
    }

    @ApiOperation("删除")
    @GetMapping("deleteById")
    public RequestResult deleteById(long id){
        int result = suggestService.deleteById(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }



    @ApiOperation("批量删除")
    @PostMapping("deleteList")
    public RequestResult deleteList(@RequestBody List<Suggest> suggests) {
        List idList = new ArrayList();
        for (Suggest s:suggests) {
            idList.add(s.getId());
        }

        int result = suggestService.deleteByList(idList);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("删除失败");
    }
    @ApiOperation("搜索")
    @GetMapping("search")
    public Map<String, Object> search(String type, String keyword, int page, int limit) {
        log.info("[search],type={},keyword={}", type, keyword);

        List<Suggest> notices = suggestService.search(type);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", notices.size());
        map.put("data", notices);
        return map;
    }


    @ApiOperation("调整状态")
    @RequestMapping("setnext")
    public RequestResult setNext(Long id){
        int result = suggestService.setNext(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("修改失败");
    }
}
