package com.axyy.service.impl;

import com.axyy.entity.Notice;
import com.axyy.mapper.NoticeMapper;
import com.axyy.service.NoticeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/4/15--14:10
 */
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> list(Integer page, Integer size) {
        String limitSql = (page - 1) * size + "," + size;
        List<Notice> notices = noticeMapper.selectList(new LambdaQueryWrapper<Notice>()
                .last("limit " + limitSql));
        return notices;
    }

    @Override
    public int add(Notice notice) {
        notice.setCreateDate(new Date());
        notice.setStatus("发送成功");
        notice.setImgurl("http://127.0.0.1:10010/" + notice.getImgurl());
        return noticeMapper.insert(notice);
    }

    @Override
    public Notice getById(Long id) {
        Notice notice = noticeMapper.selectById(id);
        return notice;
    }

    @Override
    public int getCount() {
        return noticeMapper.selectCount(null);
    }

    @Override
    public int deleteList(List idnums) {
        return noticeMapper.deleteBatchIds(idnums);

    }

    @Override
    public int deleteById(int id) {
        return noticeMapper.deleteById(id);
    }

    @Override
    public List<Notice> search(String type, String keyword) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<Notice>()
                .like(Notice::getContent, keyword)
                .last("limit 0,10");

        if (!"0".equals(type)) {
            wrapper.eq(Notice::getType, type);
        }
        List<Notice> notices = noticeMapper.selectList(wrapper);
        return notices;
    }

    @Override
    public List<Notice> listByDate(int page, int limit) {
        String limitSql = (page - 1) * limit + "," + limit;
        List<Notice> notices = noticeMapper.selectList(new LambdaQueryWrapper<Notice>()
                .last("limit " + limitSql)
                .orderByDesc(Notice::getId));
        return notices;
    }
}
