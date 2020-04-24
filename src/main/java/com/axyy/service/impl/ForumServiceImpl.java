package com.axyy.service.impl;

import com.axyy.entity.Forum;
import com.axyy.mapper.ForumMapper;
import com.axyy.service.ForumService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/4/15--14:09
 */
@Slf4j
@Service
public class ForumServiceImpl implements ForumService {

    @Resource
    private ForumMapper forumMapper;

    /**
     * 获取集合
     * @param page
     * @param size
     * @return
     */
    @Override
    public List list(int page, int size) {
        String limitSql = (page-1)*size+","+size;
        List<Forum> forums = forumMapper.selectList(new LambdaQueryWrapper<Forum>()
                .last(limitSql));
        return forums;
    }

}
