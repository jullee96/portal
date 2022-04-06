package com.hamonize.portal;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PsqlDatabaseConfig{
    @Autowired
    GlobalPropertySource gs;
    
    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
        .create()
        .url(gs.getUrl())
        .driverClassName(gs.getDriverclassname())
        .username(gs.getUsername())
        .password(gs.getPassword())
        .build();
    }
    
    @Bean
    public SqlSessionFactory SqlSessionFactory(ApplicationContext applicationContext)
            throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate SqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
