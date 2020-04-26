package com.axyy.service.impl;

import com.axyy.entity.Img;
import com.axyy.mapper.ImgMapper;
import com.axyy.service.ImgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/4/15--14:09
 */
@Slf4j
@Service
public class ImgServiceImpl implements ImgService {
    @Resource
    private ImgMapper imgMapper;

    @Override
    public List getImgs(int type, String foreignId) {
        List<Img> imgs = imgMapper.selectList(new LambdaQueryWrapper<Img>()
                .eq(Img::getType, type)
                .eq(Img::getForeignId, foreignId));
        return imgs;
    }

    @Override
    public int insert(int type, Long foreignId, String url) {
        Img img = Img.builder()
                .createTime(new Date())
                .foreignId(foreignId)
                .type(type)
                .url(url)
                .valid(1).build();
        return imgMapper.insert(img);
    }
}
