package com.project.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpiderBoot {
	public static void main(String[] args) {
		SpringApplication.run(SpiderBoot.class);
	}
}
