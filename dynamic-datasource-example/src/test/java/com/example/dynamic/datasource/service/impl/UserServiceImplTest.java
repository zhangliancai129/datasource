package com.example.dynamic.datasource.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dynamic.datasource.model.User;
import com.example.dynamic.datasource.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@TestPropertySource(locations = "classpath:application-dev.yml", properties = {"feign.hystrix.enabled=false"})
//@ContextConfiguration(classes = {UserServiceImpl.class})
//@Import(value = {
//        DynamicDataSourceConfiguration.class,
//        DruidDataSourceAutoConfigure.class,
//        MybatisPlusAutoConfiguration.class,
//        AopAutoConfiguration.class})
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void pageQueryOracleUser() {
        IPage<User> page = new Page<>(1, 10);
        userService.pageQueryOracleUser(page);
        Assert.assertNotNull(page);
    }

    @Test
    public void pageQueryMysqlUser() {
        IPage<User> page = new Page<>(1, 50);
        userService.pageQueryMysqlUser(page);
        Assert.assertNotNull(page);
    }
}