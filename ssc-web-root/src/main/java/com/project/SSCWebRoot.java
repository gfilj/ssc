package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SSCWebRoot {
    public static void main(String[] args) {
        SpringApplication.run(SSCWebRoot.class);
    }
}
