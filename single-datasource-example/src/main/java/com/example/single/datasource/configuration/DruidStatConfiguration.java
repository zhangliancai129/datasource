package com.example.single.datasource.configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidStatConfiguration {

    /**
     * Servlet
     * https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE.
     * https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_Druid%E5%92%8CSpring%E5%85%B3%E8%81%94%E7%9B%91%E6%8E%A7%E9%85%8D%E7%BD%AE
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

//        bean.addInitParameter("allow", "127.0.0.1"); // 白名单
//        bean.addInitParameter("deny", "192.168.15.21"); // 黑名单
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "Aa!23456");
        bean.addInitParameter("resetEnable", "false");

        return bean;
    }

    /**
     * Filter
     * https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_%E9%85%8D%E7%BD%AEWebStatFilter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {

        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());

        // 添加过滤规则.
        bean.addUrlPatterns("/*");

        // 排除一些不必要的url
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
}
