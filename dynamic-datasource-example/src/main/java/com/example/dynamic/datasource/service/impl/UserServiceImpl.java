package com.example.dynamic.datasource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dynamic.datasource.mapper.mysql.MysqlUserMapper;
import com.example.dynamic.datasource.mapper.oracle.OracleUserMapper;
import com.example.dynamic.datasource.model.User;
import com.example.dynamic.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OracleUserMapper oracleUserMapper;

    @Autowired
    private MysqlUserMapper mysqlUserMapper;

    @Override
    public IPage<User> pageQueryOracleUser(IPage<User> page) {
        return oracleUserMapper.queryUser(page, new HashMap<>());
    }

    @Override
    public IPage<User> pageQueryMysqlUser(IPage<User> page) {
        return mysqlUserMapper.queryUser(page, new HashMap<>());
    }
}
