package com.axyy.mapper;

import com.axyy.entity.Suggest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @date 2020/4/15--13:58
 */
@Mapper
public interface SuggestMapper extends BaseMapper<Suggest> {
    @Update("update suggest set status='已查看' where id = #{id}")
    int setNext(Long id);
}
