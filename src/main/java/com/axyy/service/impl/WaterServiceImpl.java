package com.axyy.service.impl;

import com.axyy.entity.Water;
import com.axyy.mapper.WaterMapper;
import com.axyy.service.WaterService;
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
public class WaterServiceImpl implements WaterService {

    @Resource
    private WaterMapper waterMapper;

    @Override
    public List<Water> list(int page, int size) {
        String limitSql = "limit " + (page - 1) * size + "," + size;

        List<Water> waters = waterMapper.selectList(new LambdaQueryWrapper<Water>()
                .last(limitSql));

        return waters;
    }

    @Override
    public int getCount() {
        return waterMapper.selectCount(null);
    }

    @Override
    public int deleteById(Long id) {
        return waterMapper.deleteById(id);
    }

    @Override
    public List<Water> search(String status, String orderNo) {
        LambdaQueryWrapper<Water> wrapper = new LambdaQueryWrapper<Water>()
                .like(Water::getOrderNo, orderNo)
                .last("limit 0,10");
        if(!"0".equals(status)){
            wrapper.eq(Water::getStatus,status);
        }
        return waterMapper.selectList(wrapper);
    }

    @Override
    public int setNext(Long id) {
        Water water = waterMapper.selectById(id);
        if(water!=null){
            String status = "已完成";
            if("未派单".equals(water.getStatus())){
                status = "派送中";
            }
            return waterMapper.setNext(status, id);
        }
        return 0;
    }
}
