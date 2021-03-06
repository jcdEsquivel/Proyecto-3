package com.treeseed.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan(basePackages = {"com.treeseed"})
@EnableAutoConfiguration

@EnableJpaRepositories("com.treeseed.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.treeseed.ejb")
								//uncomment this to generate war
public class TreeseedApplication extends SpringBootServletInitializer{
	
	//uncomment this to generate war
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(TreeseedApplication.class);
	    }
	
    public static void main(String[] args) {
        SpringApplication.run(TreeseedApplication.class, args);
    }
    
	@Bean	
	public FilterRegistrationBean filterRegistrationBean() {
		
		List<String> urls = new ArrayList<String>();
		urls.add("/*");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		PassthroughFilter passFilter = new PassthroughFilter();
		registrationBean.setFilter(passFilter);
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	
	@Bean	
	public FilterRegistrationBean filterRegistrationBean2() {
		
		List<String> urls = new ArrayList<String>();
		urls.add("/rest/protected/*");
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WSFilter wsFilter = new WSFilter();
		registrationBean.setFilter(wsFilter);
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	

}


