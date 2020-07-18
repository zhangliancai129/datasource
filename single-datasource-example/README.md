# 单个数据源的配置示例 + MyBatis Generator 插件

### spring boot 2.x 默认支持三种数据源(连接池)：
定义在spring-boot.jar的org.springframework.boot.jdbc.DataSourceBuilder.DATA_SOURCE_TYPE_NAMES<br>
>com.zaxxer.hikari.HikariDataSource（Spring boot默认使用的连接池）<br>
>org.apache.tomcat.jdbc.pool.DataSource<br>
>org.apache.commons.dbcp2.BasicDataSource<br>

### dataSource的配置参数
在不引入其它数据源的情况下，dataSource自动装配的几个关键类：
 + org.springframework.boot.autoconfigure.jdbc.DataSourceProperties（dataSource可配置的参数，大部分在这个类中）
 + org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration（数据源自动配置的配置类）
 + org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration（使用哪个dataSource）
 
**常用的参数：**
 + spring.datasource.type : 数据源类型
 + spring.datasource.dbcp2/hikari/tomcat.* : 指定数据源的配置参数，与下面的参数一样
 + spring.datasource.name : 给数据源取的名称
 + spring.datasource.driverClassName : 数据库驱动类
 + spring.datasource.url : 数据库连接地址
 + spring.datasource.username : 数据库用户名
 + spring.datasource.password : 数据库密码
 + spring.datasource.xa.dataSourceClassName : 指定数据源的全限定名
 + spring.datasource.xa.properties : 传递给datasource的参数值，是一个Map对象
 <br>
[配置参数参考文档](https://segmentfault.com/a/1190000004316491)

### Druid DataSource
[DruidDataSource配置属性列表](https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8)<br>
[DruidDataSource参考配置](https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE)<br>
[密码加密](https://github.com/alibaba/druid/wiki/%E4%BD%BF%E7%94%A8ConfigFilter)<br>
[druid-spring-boot-starter的使用和配置](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter)
<br>

### MyBatis：
 + org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
 + org.mybatis.spring.boot.autoconfigure.MybatisProperties