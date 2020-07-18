package com.example.multi.datasource.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 参考 com.github.pagehelper.autoconfigure.PageHelperProperties
 * <p>
 * 在启动类加上注解：@ConfigurationPropertiesScan
 */
@Component
@ConfigurationProperties(prefix = MysqlPageHelperProperties.PAGEHELPER_PREFIX)
public class MysqlPageHelperProperties {

    static final String PAGEHELPER_PREFIX = "pagehelper.mysql";

    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public String getReasonable() {
        return properties.getProperty("reasonable");
    }

    public void setReasonable(String reasonable) {
        properties.setProperty("reasonable", reasonable);
    }

    public String getSupportMethodsArguments() {
        return properties.getProperty("supportMethodsArguments");
    }

    public void setSupportMethodsArguments(String supportMethodsArguments) {
        properties.setProperty("supportMethodsArguments", supportMethodsArguments);
    }

    public String getHelperDialect() {
        return properties.getProperty("helperDialect");
    }

    public void setHelperDialect(String helperDialect) {
        properties.setProperty("helperDialect", helperDialect);
    }

    public String getParams() {
        return properties.getProperty("params");
    }

    public void setParams(String params) {
        properties.setProperty("params", params);
    }
}
