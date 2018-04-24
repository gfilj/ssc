package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by goforit on 2017/10/22.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.project","share"})
public class BootStrap {
    public static void main(String[] args) {




        SpringApplication.run(BootStrap.class);
    }
}
