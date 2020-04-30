package com.axyy.service.impl;

import com.axyy.entity.User;
import com.axyy.entity.Water;
import com.axyy.entity.vo.WaterAddVo;
import com.axyy.mapper.WaterMapper;
import com.axyy.service.UserService;
import com.axyy.service.WaterService;
import com.axyy.util.IDUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/4/15--14:10
 */
@Slf4j
@Service
public class WaterServiceImpl implements WaterService {

    @Resource
    private UserService userService;
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
        if (!"0".equals(status)) {
            wrapper.eq(Water::getStatus, status);
        }
        return waterMapper.selectList(wrapper);
    }

    @Override
    public int setNext(Long id) {
        Water water = waterMapper.selectById(id);
        if (water != null) {
            String status = "已完成";
            if ("未派单".equals(water.getStatus())) {
                status = "派送中";
            }
            return waterMapper.setNext(status, id);
        }
        return 0;
    }

    @Override
    public int addWaterByVo(WaterAddVo waterAddVo) {
        User user = userService.getById(waterAddVo.getUserid());
        Water water = Water.builder()
                .userid(waterAddVo.getUserid())
                .orderNo(IDUtil.createId() + "3")
                .content(waterAddVo.getContent())
                .price(waterAddVo.getPrice())
                .username(user.getName())
                .phone(user.getPhone())
                .address(waterAddVo.getAddress())
                .status("未派单")
                .createtime(new Date())
                .note(waterAddVo.getNote()).build();
        return waterMapper.insert(water);
    }

    @Override
    public List<Water> wxlist(int page, int size, long userid) {
        String limitSql = "limit " + (page - 1) * size + "," + size;
        List<Water> waters = waterMapper.selectList(new LambdaQueryWrapper<Water>()
                .last(limitSql)
                .eq(Water::getUserid, userid));
        return waters;
    }
}
