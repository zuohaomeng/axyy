package com.axyy.mapper;

import com.axyy.entity.Clean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @date 2020/4/15--13:53
 */
@Mapper
public interface CleanMapper extends BaseMapper<Clean> {
    @Update("update clean set status='已完成' where id=#{id}")
    int setNext(Long id);
}
