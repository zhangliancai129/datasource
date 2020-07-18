package com.example.multi.datasource.mapper.oracle;


import com.example.multi.datasource.model.User;

import java.util.List;

public interface OracleUserMapper {

    List<User> selectList();
}