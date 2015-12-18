package com.puppey.config;

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

import com.puppey.domain.Achievement;
import com.puppey.domain.Artist;
import com.puppey.domain.Comment;
import com.puppey.domain.CurrencyTransaction;
import com.puppey.domain.Group;
import com.puppey.domain.Item;
import com.puppey.domain.Matchup;
import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.News;
import com.puppey.domain.Prize;
import com.puppey.domain.Team;
import com.puppey.domain.Template;
import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;
import com.puppey.domain.UserAuthority;

@ComponentScan(basePackages = { "com.puppey.config" })
@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://104.131.247.9:3306/puppey");
        ds.setUsername("ad59543ab131");
        ds.setPassword("4852ba4cd6a062e9");
        return ds;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
        sessionBuilder.addAnnotatedClasses(Team.class, Tournament.class, Matchup.class, MatchupPrediction.class,
                TournamentPrediction.class, User.class, UserAuthority.class, Group.class, Template.class,
                CurrencyTransaction.class, Achievement.class, Comment.class, Prize.class, Item.class, News.class, Artist.class);
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
