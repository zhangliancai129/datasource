package com.example.single.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.single.datasource.mapper")
@SpringBootApplication
public class SingleDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleDatasourceApplication.class, args);
    }

}
