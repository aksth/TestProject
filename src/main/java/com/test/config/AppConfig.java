package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by intern1 on 4/27/2017.
 */

@Configuration
@PropertySource("classpath:app-config.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.test.repository")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource(){

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("connection.driver.class"));
        System.out.println(env.getProperty("connection.driver.class"));

        ds.setUrl(env.getProperty("connection.url"));
        ds.setUsername(env.getProperty("connection.username"));
        ds.setPassword(env.getProperty("connection.password"));
        return ds;

    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            ){

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("entitymanager.packages.to.scan"));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;

    }

    private Properties getHibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }


//    @Bean
//    public FarmerRepository farmerRepository(JdbcTemplate jdbcTemplate){
//        return new JdbcFarmerRepository(jdbcTemplate);
//    }

}
