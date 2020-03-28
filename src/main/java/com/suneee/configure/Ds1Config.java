package com.suneee.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "com.suneee.mapper")

public class Ds1Config {

    @Bean(name ="ds1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    @Primary
    public DataSource dataSource1(){
        return   new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactory1")
    @Primary
    public SqlSessionFactory sqlSessionFactory1 (@Qualifier("ds1")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean= new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSerchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mapper/**.xml";
        bean.setMapperLocations(resolver.getResources(packageSerchPath));
       // bean.setConfigLocation(new ClassPathResource("mapper/*.xml"));
        return bean.getObject();

    }
    @Bean(name = "dataSourceTransactionManager1")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager1(@Qualifier("ds1")DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return  transactionManager;
    }
    @Bean(name = "sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate1 (@Qualifier("sqlSessionFactory1")SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return  sqlSessionTemplate;
    }
}
