package com.example.single.datasource.mapper;

import com.example.single.datasource.model.User;

public interface UserMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}