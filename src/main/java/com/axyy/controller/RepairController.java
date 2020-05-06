package com.axyy.controller;

import cn.hutool.core.date.DateUtil;
import com.axyy.entity.Img;
import com.axyy.entity.Notice;
import com.axyy.entity.Repair;
import com.axyy.entity.vo.RepairListVo;
import com.axyy.service.ImgService;
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
    @Resource
    private ImgService imgService;

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
    @ApiOperation("调整状态")
    @RequestMapping("setworker")
    public RequestResult setworker(Long id,@RequestBody Repair repair) {
        int result = repairService.setworker(id,repair.getRepairName());
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("修改失败");
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @ApiOperation("插入数据")
    @PostMapping("addByUserId")
    public RequestResult add(@RequestBody String content, @RequestParam long userid) {
        Repair repair = new Repair();
        repair.setContent(content);
        repair.setUserid(userid);
        Long repairId = repairService.add(repair);
        if (repairId > 0) {
            return RequestResult.SUCCESS("", repairId);
        }
        return RequestResult.ERROR("添加失败");
    }

    @ApiOperation("微信保修记录")
    @GetMapping("wxlist")
    public Map<String, Object> wxlist(@RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam long userid) {
        List<Repair> list = repairService.wxlist(page, size, userid);
        List<RepairListVo> repairList = new ArrayList<RepairListVo>();
        if (list != null) {
            for (Repair r : list) {
                RepairListVo vo = RepairListVo.builder()
                        .r_id(r.getRepairNo())
                        .state(r.getStatus())
                        .repairContext(r.getContent())
                        .submitTime(DateUtil.formatDateTime(r.getCreatetime()))
                        .repairName(r.getRepairName())
                        .repairPhone(r.getRepairPhone())
                        .build();
                List<Img> imgs = imgService.getImgs(2, String.valueOf(r.getId()));
                if (imgs == null || imgs.size() <= 0) {
                    vo.setR_icon(null);
                } else {
                    vo.setR_icon("http://127.0.0.1:10010/" + imgs.get(0).getUrl());
                }
                repairList.add(vo);
            }
        }
        HashMap map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", repairList.size());
        map.put("data", repairList);
        return map;
    }

}
