package com.axyy.service.impl;

import com.axyy.entity.Repair;
import com.axyy.entity.User;
import com.axyy.mapper.RepairMapper;
import com.axyy.mapper.UserMapper;
import com.axyy.service.RepairService;
import com.axyy.service.UserService;
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
public class RepairServiceImpl implements RepairService {
    @Resource
    private RepairMapper repairMapper;
    @Resource
    private UserService userService;

    @Override
    public List list(int page, int size) {
        String limitSql = "limit " + (page - 1) * size + "," + size;
        List<Repair> repairs = repairMapper.selectList(new LambdaQueryWrapper<Repair>()
                .last(limitSql));
        return repairs;
    }

    @Override
    public int getCount() {
        return repairMapper.selectCount(null);
    }

    @Override
    public Repair getById(Long id) {
        return null;
    }

    @Override
    public Integer add(Repair repair) {
        User user = userService.getById(repair.getUserid());
        return null;
    }

    @Override
    public int deleteList(List<Repair> repairs) {
        int result = 0;
        if (repairs != null) {
            for (Repair r : repairs) {
                result += repairMapper.delete(new LambdaQueryWrapper<Repair>()
                        .eq(Repair::getRepairNo, r.getRepairNo()));
            }
        }
        return result;
    }

    @Override
    public int deleteById(int id) {
        return repairMapper.deleteById(id);
    }

    @Override
    public List<Repair> search(String status, String repairNo) {
        LambdaQueryWrapper<Repair> wrapper = new LambdaQueryWrapper<Repair>()
                .like(Repair::getRepairNo, repairNo)
                .last("limit 0,10");
        if (!"0".equals(status)) {
            wrapper.eq(Repair::getStatus, status);
        }
        return repairMapper.selectList(wrapper);
    }

    @Override
    public int setNext(Long id) {
        Repair repair = repairMapper.selectById(id);
        if (repair != null) {
            String status = "已完成";
            if ("未派单".equals(repair.getStatus())) {
                status = "维修中";
            }
            return repairMapper.setNext(status, repair.getId());
        }
        return 0;
    }
}