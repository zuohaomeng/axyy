package com.axyy.service.impl;

import com.axyy.entity.Clean;
import com.axyy.mapper.CleanMapper;
import com.axyy.service.CleanService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/4/15--14:08
 */
@Slf4j
@Service
public class CleanServiceImpl implements CleanService {
    @Resource
    private CleanMapper cleanMapper;
    @Override
    public Integer order(Clean clean) {
        Integer result = cleanMapper.insert(clean);
        return result;
    }

    @Override
    public List<Clean> list(int page, int size) {
        String limitSql = "limit " +(page-1)*size+","+size;
        List<Clean> cleans = cleanMapper.selectList(new LambdaQueryWrapper<Clean>()
                .last(limitSql));
        return cleans;
    }

    @Override
    public int getCount() {
        return cleanMapper.selectCount(null);
    }

    @Override
    public Clean getById(Long id) {
        Clean clean = cleanMapper.selectById(id);
        return clean;
    }

    @Override
    public int deleteById(Long id) {
        return cleanMapper.deleteById(id);
    }

    @Override
    public List<Clean> search(String type, String orderNo) {
        LambdaQueryWrapper<Clean> wrapper = new LambdaQueryWrapper<Clean>()
                .like(Clean::getOrderNo, orderNo)
                .last("limit 0,10");
        if(!"0".equals(type)){
            wrapper.eq(Clean::getType, type);
        }
        return cleanMapper.selectList(wrapper);
    }

    @Override
    public int setNext(Long id) {
        return cleanMapper.setNext(id);
    }
}
