package com.project.common.extension;

import com.project.common.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Created by goforit on 2017/8/28.
 */
public class YamlFileApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    Log logger = LogUtil.getLogger(getClass());

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Resource resource = applicationContext.getResource("classpath:config/projectConfig.yml");
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
            PropertySource<?> yamlTestProperties = sourceLoader.load("projectConfig", resource, null);
            applicationContext.getEnvironment().getPropertySources().addFirst(yamlTestProperties);
        } catch (Throwable e) {
//            throw new RuntimeException(e);
            logger.error(LogUtil.logstr("解析配置文件出错", "classpath:config/projectConfig.yml", "配置文件不存在"));
        }
    }
}
