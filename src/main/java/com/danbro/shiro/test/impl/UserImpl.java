package com.danbro.shiro.test.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.shiro.test.enity.User;
import com.danbro.shiro.test.mapper.UserMapper;
import com.danbro.shiro.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserImpl
 * @Description TODO
 * @Date 2019/12/26 13:16
 * @Author Danrbo
 */
@Service
public class UserImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User selectUserByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return userMapper.selectOne(userQueryWrapper);
    }
}
