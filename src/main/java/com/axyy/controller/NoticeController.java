package com.axyy.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.axyy.entity.Notice;
import com.axyy.entity.vo.NoticeVo;
import com.axyy.service.NoticeService;
import com.axyy.util.RequestResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @date 2020/4/15--14:19
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Value("${web.upload-path}")
    String filePath;
    @Resource
    private NoticeService noticeService;

    @ApiOperation("list")
    @GetMapping("list")
    public Map<String, Object> list(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int limit) {
        List<Notice> notices = noticeService.list(page, limit);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", noticeService.getCount());
        map.put("data", notices);
        return map;
    }

    @ApiOperation("添加文章")
    @PostMapping("add")
    public RequestResult add(@RequestBody Notice notice) {
        Integer result = noticeService.add(notice);
        if (result > 0) {
            return RequestResult.SUCCESS();
        }
        return RequestResult.ERROR("添加失败！");
    }

    @ApiOperation("get")
    @GetMapping("get")
    public RequestResult get(Long id) {
        Notice notice = noticeService.getById(id);
        return RequestResult.SUCCESS(notice);
    }
    @ApiOperation("批量删除")
    @PostMapping("deleteList")
    public RequestResult deleteList(@RequestBody List<Notice> notices) {
        List list = new ArrayList();
        for (Notice notice : notices) {
            list.add(notice.getId());
        }
        int result = noticeService.deleteList(list);
        if (result > 0) {
            return RequestResult.SUCCESS("删除成功！");
        }

        return RequestResult.ERROR("删除失败！");
    }

    @ApiOperation("根据id删除")
    @GetMapping("deleteById")
    public RequestResult deleteById(int id) {

        int result = noticeService.deleteById(id);
        if (result > 0) {
            return RequestResult.SUCCESS("删除成功！");
        }
        return RequestResult.ERROR("删除失败！");
    }

    @ApiOperation("搜索")
    @GetMapping("search")
    public Map<String, Object> search(String type, String keyword, int page, int limit) {
        log.info("[search],type={},keyword={}", type, keyword);

        List<Notice> notices = noticeService.search(type, keyword);

        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "成功");
        map.put("count", notices.size());
        map.put("data", notices);
        return map;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>小程序
    @ApiOperation("根据时间排序")
    @GetMapping("listByDate")
    public RequestResult listByDate(@RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int limit) {
        List<Notice> notices = noticeService.listByDate(page, limit);
        List<NoticeVo> noticeVos = new ArrayList<>();
        for (Notice n: notices) {
            noticeVos.add(convertToVo(n));
        }
        return RequestResult.SUCCESS(noticeVos);
    }

    @ApiOperation("getVo")
    @GetMapping("getVo")
    public RequestResult getVo(Long id) {
        Notice notice = noticeService.getById(id);

        return RequestResult.SUCCESS(convertToVo(notice));
    }
    private NoticeVo convertToVo(Notice notice){
        return NoticeVo.builder()
                .id(notice.getId())
                .content(notice.getContent())
                .createDate(DateUtil.format(notice.getCreateDate(), "MM月dd日"))
                .imgurl(notice.getImgurl())
                .status(notice.getStatus())
                .title(notice.getTitle())
                .type(notice.getType()).build();
    }
}
