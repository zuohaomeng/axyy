package com.axyy.service.impl;

import com.axyy.entity.Suggest;
import com.axyy.mapper.SuggestMapper;
import com.axyy.service.SuggestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/4/15--14:10
 */
@Slf4j
@Service
public class SuggestServiceImpl implements SuggestService {

    @Resource
    private SuggestMapper suggestMapper;

    @Override
    public List<Suggest> list(int page, int size) {
        String limitSql = "limit " + (page - 1) * size + "," + size;
        List<Suggest> repairs = suggestMapper.selectList(new LambdaQueryWrapper<Suggest>()
                .last(limitSql));
        return repairs;
    }

    @Override
    public int getCount() {
        return suggestMapper.selectCount(null);
    }

    @Override
    public int deleteById(Long id) {
        return suggestMapper.deleteById(id);
    }

    @Override
    public int deleteByList(List list) {
        return suggestMapper.deleteBatchIds(list);
    }

    @Override
    public int setNext(Long id) {
        return suggestMapper.setNext(id);
    }

    @Override
    public List<Suggest> search(String type) {

        LambdaQueryWrapper<Suggest> wrapper = new LambdaQueryWrapper<Suggest>()
                .last("limit 0,10");
        if (!"0".equals(type)) {
            wrapper.eq(Suggest::getType, type);
        }
        List<Suggest> notices = suggestMapper.selectList(wrapper);
        return notices;
    }
}
