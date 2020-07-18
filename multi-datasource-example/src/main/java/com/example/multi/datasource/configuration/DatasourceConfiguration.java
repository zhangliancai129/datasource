package com.example.multi.datasource.configuration;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.core.io.Resource;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class DatasourceConfiguration {

    protected SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        // 分页插件
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(this.getPageHelperProperties());

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(this.getMyBatisMapperLocations());
        sqlSessionFactoryBean.setTypeAliasesPackage(this.getMyBatisTypeAliasesPackage());
        sqlSessionFactoryBean.setPlugins(interceptor);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 事务管理，哪些方法需要自动添加事务
     *
     * @param transactionManager
     * @return
     */
    protected TransactionInterceptor transactionAdvice(TransactionManager transactionManager) {
        // 只读，不新建事务
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly(true);
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        // 如果当前没有事务，就新建一个事务。
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setRollbackRules(Arrays.asList(new RollbackRuleAttribute(Exception.class)));
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        Map<String, TransactionAttribute> methodNameMap = new HashMap<>(32);
        methodNameMap.put("save*", required);
        methodNameMap.put("batchSave*", required);
        methodNameMap.put("insert*", required);
        methodNameMap.put("batchInsert*", required);
        methodNameMap.put("add*", required);
        methodNameMap.put("update*", required);
        methodNameMap.put("set*", required);
        methodNameMap.put("modify*", required);
        methodNameMap.put("edit*", required);
        methodNameMap.put("delete*", required);
        methodNameMap.put("remove*", required);
        methodNameMap.put("query*", required);
        methodNameMap.put("select*", required);
        methodNameMap.put("find*", required);
        methodNameMap.put("get*", required);
        methodNameMap.put("search*", required);

        NameMatchTransactionAttributeSource nameMatchSource = new NameMatchTransactionAttributeSource();
        nameMatchSource.setNameMap(methodNameMap);

        return new TransactionInterceptor(transactionManager, nameMatchSource);
    }

    /**
     * 事务管理AOP相关配置
     *
     * @param transactionInterceptor
     * @return
     */
    protected Advisor transactionAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(this.getTransactionPointcutExpression());
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }

    protected abstract Properties getPageHelperProperties();

    protected abstract Resource[] getMyBatisMapperLocations();

    protected abstract String getMyBatisTypeAliasesPackage();

    protected abstract String getTransactionPointcutExpression();
}
