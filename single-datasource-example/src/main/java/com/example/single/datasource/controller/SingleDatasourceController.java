package com.example.single.datasource.controller;

import com.example.single.datasource.model.User;
import com.example.single.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
public class SingleDatasourceController {

    @Autowired
    private UserService userService;

    /**
     * 数据源监控：http://localhost:8083/single-datasource-example/druid/index.html
     * <p>
     * 当前接口路径：http://localhost:8083/single-datasource-example/user/getById?id=eccf737f-e948-4bdf-9941-b4d1dced672a
     *
     * @param id
     * @return
     */
    @GetMapping("getById")
    public User getById(@RequestParam("id") String id) {
        return userService.selectById(id);
    }
}
