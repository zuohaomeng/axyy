package com.axyy.mapper;

import com.axyy.entity.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @date 2020/4/15--13:57
 */
@Mapper
public interface RepairMapper extends BaseMapper<Repair> {

    @Update("update  repair set status=#{status} where id=#{id}")
    int setNext(String status,long id);
}
