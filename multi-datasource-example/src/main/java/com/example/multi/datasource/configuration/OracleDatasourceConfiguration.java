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
import org.springframework.context.annotation.Primary;
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
        basePackages = {"com.example.multi.datasource.mapper.oracle"},
        sqlSessionFactoryRef = "oracleSqlSessionFactory"
)
public class OracleDatasourceConfiguration extends DatasourceConfiguration {

    @Value("${mybatis.oracle.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.oracle.type-aliases-package}")
    private String typeAliasesPackage;

    @Value("${datasource.oracle.transactionPointcutExpression}")
    private String transactionPointcutExpression;

    @Autowired
    private OraclePageHelperProperties oraclePageHelperProperties;

    @Primary
    @Bean(name = "oracleDatasource", initMethod = "init")
    @ConfigurationProperties(prefix = "datasource.oracle")
    public DruidDataSource oracleDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("oracleTransactionManager")
    public TransactionManager oracleTransactionManager(@Qualifier("oracleDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("oracleTransactionAdvice")
    public TransactionInterceptor oracleTransactionAdvice(@Qualifier("oracleTransactionManager") TransactionManager transactionManager) {
        return super.transactionAdvice(transactionManager);
    }

    @Primary
    @Bean("oracleTransactionAdvisor")
    public Advisor oracleTransactionAdvisor(@Qualifier("oracleTransactionAdvice") TransactionInterceptor transactionInterceptor) {
        return super.transactionAdvisor(transactionInterceptor);
    }

    @Primary
    @Bean("oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDatasource") DataSource dataSource) throws Exception {
        return super.createSqlSessionFactory(dataSource);
    }

    @Primary
    @Bean("oracleSqlSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Override
    protected Properties getPageHelperProperties() {
        return oraclePageHelperProperties.getProperties();
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
