package com.axyy.service.impl;

import com.axyy.entity.Discuss;
import com.axyy.mapper.DiscussMapper;
import com.axyy.service.DiscussService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/4/15--14:09
 */
@Slf4j
@Service
public class DiscussServiceImpl implements DiscussService {

    @Resource
    private DiscussMapper discussMapper;

    @Override
    public List getFromForum(Long forumId) {
        List<Discuss> discusses = discussMapper.selectList(new LambdaQueryWrapper<Discuss>()
                .eq(Discuss::getForumId, forumId));
        return discusses;
    }

    /**
     * 添加评论
     * @param discuss
     * @return
     */
    @Override
    public Integer add(Discuss discuss) {
        int result = discussMapper.insert(discuss);
        return result;
    }

}
