package com.example.dynamic.datasource.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

/**
 * 配置AOP，根据不同的包切换不同的数据源
 */
@Aspect
@Component
public class DataSourceSwitchAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Pointcut("execution(* com.example.dynamic.datasource.mapper.oracle..*.*(..))")
    public void oraclePointcut() {
    }

    @Pointcut("execution(* com.example.dynamic.datasource.mapper.mysql..*.*(..))")
    public void mysqlPointcut() {
    }

    @Before("oraclePointcut()")
    public void switchToOracle(JoinPoint joinPoint) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("切换到数据源: {}", DataSourceKey.ORACLE);
        }

        DataSourceContextHolder.setDataSourceKey(DataSourceKey.ORACLE);
    }

    @Before("mysqlPointcut()")
    public void switchToMysql(JoinPoint joinPoint) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("切换到数据源: {}", DataSourceKey.MYSQL);
        }

        DataSourceContextHolder.setDataSourceKey(DataSourceKey.MYSQL);
    }
}
