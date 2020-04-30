package com.axyy.service;

import com.axyy.entity.DianZan;

import java.util.List;

public interface DianZanService {
    int like(long userid, long forumid);
    List<DianZan> getListByForumid(long forumid);
}
