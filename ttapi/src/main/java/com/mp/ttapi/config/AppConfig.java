package com.mp.ttapi.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mp.ttapi.domain.FileTranslation;
import com.mp.ttapi.domain.ImageChecksum;

@ComponentScan(basePackages = { "com.mp.ttapi.config" })
@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://us-cdbr-iron-east-03.cleardb.net:3306/heroku_dd00d9c9d065aef");
        ds.setUsername("bd50b7900ba27a");
        ds.setPassword("a01f13ba");
        return ds;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionBuilder.addAnnotatedClasses(ImageChecksum.class, FileTranslation.class);
        sessionBuilder.addProperties(getHibernateProperties());
       return sessionBuilder.buildSessionFactory();
    }
    
    public Properties getHibernateProperties(){
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        return props;
    }
    
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager tm = new HibernateTransactionManager(sessionFactory);
        return tm;
    }
    
    @Bean
    protected CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver mpr = new CommonsMultipartResolver();
        mpr.setMaxUploadSize(10240000);
        return mpr;
    }
}