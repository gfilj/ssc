package com.netease.fileupload;

import com.netease.common.privilege.client.authz.filter.BusinessAuthzFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Created by goforit on 2017/9/1.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class BootStrap {
    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
    }

    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
            FilterRegistrationBean registration = new FilterRegistrationBean(new BusinessAuthzFilter());
            registration.addUrlPatterns("/*");
            return registration;
    }
}

