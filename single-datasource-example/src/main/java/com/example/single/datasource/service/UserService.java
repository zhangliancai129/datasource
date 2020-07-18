package com.example.single.datasource.service;

import com.example.single.datasource.model.User;

public interface UserService {

    User selectById(String id);
}
