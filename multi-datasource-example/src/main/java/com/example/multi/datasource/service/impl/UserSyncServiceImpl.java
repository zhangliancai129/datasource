package com.example.multi.datasource.service.impl;

import com.example.multi.datasource.mapper.mysql.MysqlUserMapper;
import com.example.multi.datasource.mapper.oracle.OracleUserMapper;
import com.example.multi.datasource.model.User;
import com.example.multi.datasource.service.UserSyncService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSyncServiceImpl implements UserSyncService {

    @Autowired(required = false)
    private OracleUserMapper oracleUserMapper;

    @Autowired(required = false)
    private MysqlUserMapper mysqlUserMapper;

    @Transactional
    @Override
    public void sync() {
        Page<User> page = PageHelper.startPage(1, 10, true);
        oracleUserMapper.selectList();
        List<User> users = page.getResult();

        for (User user : users) {
            mysqlUserMapper.insert(user);
        }
    }
}
