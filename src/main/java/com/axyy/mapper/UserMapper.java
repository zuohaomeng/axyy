package com.axyy.mapper;

import com.axyy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @date 2020/4/15--13:58
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select id, name,phone,building,unit,apartment from user where id =#{id}")
    User getInformationById(@Param("id") Long id);

    @Update("update user set name=#{user.name},phone=#{user.phone},building=#{user.building}," +
            "unit=#{user.unit},apartment=#{user.apartment} " +
            "where openid=#{user.openid}")
    Integer saveUserByOpenId(@Param("user") User user);
}
