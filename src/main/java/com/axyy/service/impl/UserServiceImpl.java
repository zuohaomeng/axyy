package com.axyy.service.impl;

import com.axyy.entity.User;
import com.axyy.entity.Water;
import com.axyy.mapper.UserMapper;
import com.axyy.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date 2020/4/15--14:10
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public User getInformationById(Long id) {
        User user = userMapper.getInformationById(id);
        return user;
    }

    @Override
    public Integer login(User user) {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword())
                .eq(User::getType, user.getType()));
        if (users == null || users.size() <= 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public User getUserByOpenid(String openid) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getOpenid, openid)
                .last("limit 1"));
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public int saveUserByOpenId(User user) {
        User user2 = getUserByOpenid(user.getOpenid());
        if (user2 == null) {
            return userMapper.insert(user);
        } else {
            return userMapper.saveUserByOpenId(user);
        }
    }

    @Override
    public List<User> list(int page, int size) {
        String limitSql = "limit " + (page - 1) * size + "," + size;
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>()
                .last(limitSql));

        return users;
    }

    @Override
    public int getCount() {
        return userMapper.selectCount(null);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public List<User> search(String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .like(User::getName,name)
                .last("limit 0,10");
        return userMapper.selectList(wrapper);
    }
}
