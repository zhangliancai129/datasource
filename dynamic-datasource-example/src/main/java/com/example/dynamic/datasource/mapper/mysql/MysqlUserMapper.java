package com.example.dynamic.datasource.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.dynamic.datasource.model.User;

import java.util.Map;

public interface MysqlUserMapper extends BaseMapper<User> {

    IPage<User> queryUser(IPage<User> page, Map<String, Object> params);
}
