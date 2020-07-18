package com.example.multi.datasource;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication(exclude = {PageHelperAutoConfiguration.class})
public class MultiDatasourceExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceExampleApplication.class, args);
    }

}
