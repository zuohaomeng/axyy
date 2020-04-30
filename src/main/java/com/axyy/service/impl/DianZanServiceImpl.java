package com.axyy.service.impl;

import com.axyy.entity.DianZan;
import com.axyy.entity.User;
import com.axyy.mapper.DianZanMapper;
import com.axyy.service.DianZanService;
import com.axyy.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DianZanServiceImpl implements DianZanService {
    @Resource
    private UserService userService;
    @Resource
    private DianZanMapper dianZanMapper;

    @Override
    public int like(long userid, long forumid) {
        DianZan dianZan = dianZanMapper.selectOne(new LambdaQueryWrapper<DianZan>()
                .eq(DianZan::getLikeuserid, userid)
                .eq(DianZan::getForumid, forumid)
                .last("limit 1"));
        if (dianZan != null) {
            return 0;
        }
        User user = userService.getById(userid);
        DianZan like = DianZan.builder()
                .forumid(forumid)
                .likeuserid(userid)
                .likename(user.getName())
                .build();
        int result = dianZanMapper.insert(like);
        return result;
    }

    @Override
    public List<DianZan> getListByForumid(long forumid) {
        List<DianZan> dianZans = dianZanMapper.selectList(new LambdaQueryWrapper<DianZan>()
                .eq(DianZan::getForumid, forumid)
                .last("limit 3")
                .select(DianZan::getLikename)
                .orderByDesc(DianZan::getId));
        return dianZans;
    }
}
