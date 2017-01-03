package com.eficacia.configuration;

import java.io.IOException;


import javax.servlet.ServletContext;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.eficacia")
public class EficaciaConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getXmlViewResolver() {
		 XmlViewResolver resolver = new XmlViewResolver();
		 resolver.setOrder(0);
	     resolver.setLocation(new ServletContextResource(servletContext,"/WEB-INF/spring-excel-views.xml"));
	     return resolver;
	}
	//
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/core/");
	}
	
	@Bean
	public MessageSource messageSource() {
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("messages");
	        return messageSource;
	}
	 
	@Autowired
	ServletContext servletContext;
	 
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	     CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	     resolver.setDefaultEncoding("utf-8");
	     return resolver;
	}
	
	@Bean
	public static EnvironmentStringPBEConfig environmentVariablesConfiguration() {
	   EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
	   config.setAlgorithm("PBEWITHMD5ANDDES");
	   config.setPassword(System.getProperty("java.metproa.secureproperties"));
	   return config;
	}
	
	@Bean
	public static PooledPBEStringEncryptor stringEncryptor() {
	   PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	   encryptor.setPoolSize(1);
	   encryptor.setConfig(environmentVariablesConfiguration());
	   return encryptor;
	}
	
	@Bean
    public static EncryptablePropertyPlaceholderConfigurer ppc() throws IOException {
		EncryptablePropertyPlaceholderConfigurer ppc = new EncryptablePropertyPlaceholderConfigurer(stringEncryptor());
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("classpath:application.properties");
        ppc.setLocation(resource);
        return ppc;
    }
	
}
