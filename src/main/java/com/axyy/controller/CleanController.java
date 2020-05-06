package com.axyy.controller;

import cn.hutool.core.date.DateUtil;
import com.axyy.entity.Clean;
import com.axyy.entity.Img;
import com.axyy.entity.Repair;
import com.axyy.entity.vo.CleanAddVo;
import com.axyy.entity.vo.CleanListVo;
import com.axyy.entity.vo.RepairListVo;
import com.axyy.service.CleanService;
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
    @ApiOperation("设置完成")
    @GetMapping("setok")
    public RequestResult setOk(Long id){
        int result = cleanService.setOk(id);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("设置失败");
    }
    @ApiOperation("设置完成")
    @PostMapping("setworker")
    public RequestResult setworker(Long id,@RequestBody Clean clean){
        int result = cleanService.setworker(id,clean.getCleanname());
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("设置失败");
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @ApiOperation("插入数据")
    @PostMapping("addByUserId")
    public RequestResult add(@RequestBody CleanAddVo cleanAddVo, @RequestParam long userid) {
        int result = cleanService.addCleanByVo(cleanAddVo);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("添加失败");
    }

    @ApiOperation("微信保修记录")
    @GetMapping("wxlist")
    public Map<String, Object> wxlist(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam long userid) {
        List<Clean> list = cleanService.wxlist(page, size, userid);
        List<CleanListVo> cleanList = new ArrayList();
        for (Clean c:list) {
            CleanListVo vo = CleanListVo.builder()
                    .b_id(c.getOrderNo())
                    .b_price(c.getPrice())
                    .b_time(DateUtil.format(c.getWorktime(), "yyyy-MM-dd hh:mm"))
                    .b_title(c.getType())
                    .state(c.getStatus())
                    .cleanname(c.getCleanname())
                    .cleanphone(c.getCleanphone()).build();
            if("日式精细擦窗".equals(vo.getB_title())){
                vo.setB_icon("../../images/clean1-3.jpg");
            }else if("居家保洁".equals(vo.getB_title())) {
                vo.setB_icon("../../images/clean1-1.jpg");
            }else if("卫生间消毒".equals(vo.getB_title())) {
                vo.setB_icon("../../images/clean1-4.jpg");
            }else {
                vo.setB_icon("../../images/clean1-2.jpg");
            }
            cleanList.add(vo);
        }
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", cleanList.size());
        map.put("data", cleanList);
        return map;
    }
}
