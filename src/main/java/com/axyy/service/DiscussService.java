package com.axyy.service;

import com.axyy.entity.Discuss;

import java.util.List;

/**
 * @date 2020/4/15--14:00
 */
public interface DiscussService {
    /**
     * 获取动态下所有的评论
     * @param forumId
     * @return
     */
    List getFromForum(Long forumId);

    Integer add(Discuss discuss);
}
