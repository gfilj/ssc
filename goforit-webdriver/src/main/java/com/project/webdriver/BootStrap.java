package com.project.webdriver;

import com.project.webdriver.extension.SpringUtil;
import com.project.webdriver.service.BiBoHuiGrab;
import com.project.webdriver.service.IcoInfoGrab;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by goforit on 2017/8/26.
 */
@SpringBootApplication
@EnableScheduling
public class BootStrap {
    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
        SpringUtil.getBean(BiBoHuiGrab.class).run();
    }
}
