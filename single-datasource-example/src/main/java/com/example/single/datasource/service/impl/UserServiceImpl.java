package com.example.single.datasource.service.impl;

import com.example.single.datasource.mapper.UserMapper;
import com.example.single.datasource.model.User;
import com.example.single.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
