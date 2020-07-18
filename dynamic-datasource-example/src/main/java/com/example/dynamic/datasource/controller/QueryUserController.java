package com.example.dynamic.datasource.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dynamic.datasource.model.User;
import com.example.dynamic.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "pageQueryOracle/{page}/{pageSize}", method = RequestMethod.GET)
    public IPage<User> pageQueryOracleUser(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return userService.pageQueryOracleUser(new Page<>(page, pageSize));
    }

    @RequestMapping(value = "pageQueryMysql/{page}/{pageSize}", method = RequestMethod.GET)
    public IPage<User> pageQueryMysqlUser(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return userService.pageQueryMysqlUser(new Page<>(page, pageSize));
    }
}
