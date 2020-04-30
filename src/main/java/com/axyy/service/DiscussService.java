package com.axyy.service;

import com.axyy.entity.DianZan;
import com.axyy.entity.Discuss;

import java.util.List;

/**
 * @date 2020/4/15--14:00
 */
public interface DiscussService {


    Integer add(Discuss discuss);

    List<Discuss> getListByForumid(long forumid);
}
