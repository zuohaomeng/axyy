package com.axyy.service;

import com.axyy.entity.Forum;
import com.axyy.entity.vo.ForumListwxVO;

import java.util.List;

/**
 * @date 2020/4/15--14:07
 */
public interface ForumService {
    List list(int page, int size);

    Long add(Forum forum);

    List<ForumListwxVO> wxlist(int page, int size);
}
