package com.axyy.service.impl;

import cn.hutool.core.date.DateUtil;
import com.axyy.component.Worker;
import com.axyy.entity.Clean;
import com.axyy.entity.User;
import com.axyy.entity.Water;
import com.axyy.entity.vo.CleanAddVo;
import com.axyy.mapper.CleanMapper;
import com.axyy.mapper.UserMapper;
import com.axyy.service.CleanService;
import com.axyy.service.UserService;
import com.axyy.util.IDUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @date 2020/4/15--14:08
 */
@Slf4j
@Service
public class CleanServiceImpl implements CleanService {
    @Resource
    private UserService userService;
    @Resource
    private CleanMapper cleanMapper;

    @Override
    public Integer order(Clean clean) {
        Integer result = cleanMapper.insert(clean);
        return result;
    }

    @Override
    public List<Clean> list(int page, int size) {
        String limitSql = "limit " + (page - 1) * size + "," + size;
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
        if (!"0".equals(type)) {
            wrapper.eq(Clean::getType, type);
        }
        return cleanMapper.selectList(wrapper);
    }

    @Override
    public int setOk(Long id) {
        return cleanMapper.setOk(id);
    }

    @Override
    public int addCleanByVo(CleanAddVo cleanAddVo) {
        User user = userService.getById(cleanAddVo.getUserid());
        Clean clean = Clean.builder()
                .userid(cleanAddVo.getUserid())
                .orderNo(IDUtil.createId() + "2")
                .type(cleanAddVo.getType())
                .worktime(DateUtil.parse(cleanAddVo.getWorktime(), "yyyy-MM-dd hh:mm"))
                .price(cleanAddVo.getPrice())
                .username(cleanAddVo.getName())
                .phone(cleanAddVo.getPhone())
                .status("已下单")
                .build();
        if (StringUtils.isEmpty(cleanAddVo.getAddress())) {
            clean.setAddress(user.getBuilding() + user.getUnit() + user.getApartment());
        } else {
            clean.setAddress(cleanAddVo.getAddress());
        }
        return cleanMapper.insert(clean);
    }

    @Override
    public List<Clean> wxlist(int page, int size, long userid) {
        String limitSql = "limit " + (page - 1) * size + "," + size;
        List<Clean> cleans = cleanMapper.selectList(new LambdaQueryWrapper<Clean>()
                .last(limitSql)
                .eq(Clean::getUserid, userid));
        return cleans;
    }

    @Override
    public int setworker(Long id, String workerName) {
        String workerPhone  = (String)Worker.workers.get(workerName);
        int result = cleanMapper.setWorker(id, workerName, workerPhone);
        return result;
    }

}
