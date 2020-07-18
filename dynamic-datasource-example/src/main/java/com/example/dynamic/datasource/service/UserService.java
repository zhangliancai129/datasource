package com.example.dynamic.datasource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dynamic.datasource.model.User;

public interface UserService {

    IPage<User> pageQueryOracleUser(IPage<User> page);

    IPage<User> pageQueryMysqlUser(IPage<User> page);
}
