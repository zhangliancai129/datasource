package com.example.multi.datasource.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@MapperScan(
        basePackages = {"com.example.multi.datasource.mapper.mysql"},
        sqlSessionFactoryRef = "mysqlSqlSessionFactory"
)
public class MysqlDatasourceConfiguration extends DatasourceConfiguration {

    @Value("${mybatis.mysql.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.mysql.type-aliases-package}")
    private String typeAliasesPackage;

    @Value("${datasource.mysql.transactionPointcutExpression}")
    private String transactionPointcutExpression;

    @Autowired
    private MysqlPageHelperProperties mysqlPageHelperProperties;

    @Bean(name = "mysqlDatasource", initMethod = "init")
    @ConfigurationProperties(prefix = "datasource.mysql")
    public DruidDataSource mysqlDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("mysqlTransactionManager")
    public TransactionManager mysqlTransactionManager(@Qualifier("mysqlDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("mysqlTransactionAdvice")
    public TransactionInterceptor mysqlTransactionAdvice(@Qualifier("mysqlTransactionManager") TransactionManager transactionManager) {
        return super.transactionAdvice(transactionManager);
    }

    @Bean("mysqlTransactionAdvisor")
    public Advisor mysqlTransactionAdvisor(@Qualifier("mysqlTransactionAdvice") TransactionInterceptor transactionInterceptor) {
        return super.transactionAdvisor(transactionInterceptor);
    }

    @Bean("mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDatasource") DataSource dataSource) throws Exception {
        return super.createSqlSessionFactory(dataSource);
    }

    @Bean("mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    protected Properties getPageHelperProperties() {
        return mysqlPageHelperProperties.getProperties();
    }

    @Override
    protected Resource[] getMyBatisMapperLocations() {
        try {
            return new PathMatchingResourcePatternResolver().getResources(mapperLocations);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected String getMyBatisTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    @Override
    protected String getTransactionPointcutExpression() {
        return transactionPointcutExpression;
    }
}
