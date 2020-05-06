package com.axyy.service.impl;

import cn.hutool.core.date.DateUtil;
import com.axyy.entity.*;
import com.axyy.entity.vo.ForumListwxVO;
import com.axyy.mapper.ForumMapper;
import com.axyy.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @date 2020/4/15--14:09
 */
@Slf4j
@Service
public class ForumServiceImpl implements ForumService {
    private static ExecutorService pool = Executors.newFixedThreadPool(3);


    @Resource
    private ForumMapper forumMapper;
    @Resource
    private ImgService imgService;
    @Resource
    private UserService userService;
    @Resource
    private DianZanService dianZanService;
    @Resource
    private DiscussService discussService;

    /**
     * 获取集合
     * @param page
     * @param size
     * @return
     */
    @Override
    public List list(int page, int size) {

        String limitSql = "limit "+(page-1)*size+","+size;
        List<Forum> forums = forumMapper.selectList(new LambdaQueryWrapper<Forum>()
                .orderByDesc(Forum::getId)
                .last(limitSql));
        return forums;
    }

    @Override
    public Long add(Forum forum) {
        forumMapper.insert(forum);
        return forum.getId();
    }

    @Override
    public List<ForumListwxVO> wxlist(int page, int size) {
        CountDownLatch latch = new CountDownLatch(3);
        List<Forum> forums = list(page, size);
        List<ForumListwxVO> forumlistVos = new ArrayList<>();
        for (Forum f:forums) {
            ForumListwxVO forumListwxVO = ForumListwxVO.builder()
                    .id(f.getId())
                    .userid(f.getUserid())
                    .address(f.getAddress())
                    .content(f.getContent())
                    .time(DateUtil.format(f.getCreateTime(), "yyyy-MM-dd hh:mm"))
                    .build();
            forumlistVos.add(forumListwxVO);
        }
        //添加昵称
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for (ForumListwxVO f:forumlistVos) {
                    User user = userService.getById(f.getUserid());
                    if(user!=null){
                        f.setNickname(user.getName());
                        f.setHeadimgurl(user.getHeadImg());
                    }
                }
                latch.countDown();
            }
        });
        //添加图片
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for (ForumListwxVO f:forumlistVos) {
                    List<Img> imgs = imgService.getImgs(1, String.valueOf(f.getId()));
                    f.setImg(imgs);
                }
                latch.countDown();
            }
        });
        //添加点赞
        pool.submit(new Runnable() {
            @Override
            public void run() {
                for (ForumListwxVO f:forumlistVos) {
                    List<DianZan> dianZans = dianZanService.getListByForumid(f.getId());
                    List<String> list = new ArrayList<>();
                    if(dianZans!=null&&dianZans.size()>0){
                        for (DianZan d:dianZans) {
                            list.add(d.getLikename());
                        }
                    }
                    f.setDianzan(list);
                }
                latch.countDown();
            }
        });
        //添加评论
        for (ForumListwxVO f:forumlistVos) {
            List<Discuss> list = discussService.getListByForumid(f.getId());
            f.setPinglun(list);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return forumlistVos;

    }

}
