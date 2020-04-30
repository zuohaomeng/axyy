package com.axyy.controller;

import cn.hutool.core.date.DateUtil;
import com.axyy.entity.Img;
import com.axyy.entity.Repair;
import com.axyy.entity.Water;
import com.axyy.entity.vo.CleanAddVo;
import com.axyy.entity.vo.RepairListVo;
import com.axyy.entity.vo.WaterAddVo;
import com.axyy.entity.vo.WaterListVo;
import com.axyy.service.WaterService;
import com.axyy.util.RequestResult;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @ApiOperation("插入数据")
    @PostMapping("addByUserId")
    public RequestResult add(@RequestBody WaterAddVo waterAddVo,@RequestParam long userid) {
        int result = waterService.addWaterByVo(waterAddVo);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("添加失败");
    }

    @ApiOperation("微信订水记录")
    @GetMapping("wxlist")
    public Map<String, Object> wxlist(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam long userid) {
        List<Water> list = waterService.wxlist(page, size, userid);
        List<WaterListVo> waterList = new ArrayList();

        for (Water w:list) {
            WaterListVo vo = WaterListVo.builder()
                    .state(w.getStatus())
                    .w_id(w.getOrderNo())
                    .w_note(w.getNote())
                    .w_title(w.getContent())
                    .w_price(w.getPrice())
                    .w_time(DateUtil.format(w.getCreatetime(), "yyyy-MM-dd hh:mm"))
                    .build();
            String icon = "";
            String content = w.getContent();
            if(content!=null){
                if(content.contains("哇哈哈")){
                    icon ="../../images/w1.jpg";
                }else if(content.contains("蓝光")){
                    icon ="../../images/w2.jpg";
                }else if(content.contains("恒大")){
                    icon ="../../images/w3.jpg";
                }else {
                    icon ="../../images/w4.jpg";
                }
            }
            vo.setW_icon(icon);
            waterList.add(vo);
        }


        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", waterList.size());
        map.put("data", waterList);
        return map;
    }
}
