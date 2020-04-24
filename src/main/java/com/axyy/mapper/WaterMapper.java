package com.axyy.mapper;

import com.axyy.entity.Water;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @date 2020/4/15--13:59
 */
@Mapper
public interface WaterMapper extends BaseMapper<Water> {
    @Update("update water set status=#{status} where id =#{id}")
    int setNext(String status,Long id);
}
