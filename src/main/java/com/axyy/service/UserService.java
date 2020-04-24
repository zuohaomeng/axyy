package com.axyy.service;

import com.axyy.entity.User;
import com.axyy.entity.Water;

import java.util.List;

/**
 * @date 2020/4/15--14:08
 */
public interface UserService {
    User getById(Long id);

    /**
     * 根据id获取联系信息
     * @return
     */
    User getInformationById(Long id);

    Integer login(User user);

    User getUserByOpenid(String openid);

    int saveUserByOpenId(User user);

    List<User> list(int page, int size);

    int getCount();

    int deleteById(Long id);

    List<User> search(String name);
}
