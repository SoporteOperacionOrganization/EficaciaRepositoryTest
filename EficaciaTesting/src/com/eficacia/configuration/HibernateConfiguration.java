package com.eficacia.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.eficacia.configuration"})
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {
	
	@Autowired
    private Environment environment;
	
	@Value("${jdbc.driverClassName}")
	private String dbClassName;
	
	@Value("${jdbc.url}")
	private String dbUrl;
	
	@Value("${jdbc.username}")
	private String dbUsername;
	
	@Value("${jdbc.password}")
	private String dbPassword;
	
	@Value("${hibernate.dialect}")
	private String dbDialect;
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.eficacia.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setDriverClassName(dbClassName);
        //dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUrl(dbUrl);
        //dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setUsername(dbUsername);
        //dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        //properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.dialect", dbDialect);
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }

	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
	
}
