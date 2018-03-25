package com.project.config;

import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.project.common.util.LogUtil.getLogger;
import static com.project.common.util.LogUtil.logstr;

/**
 * Created by goforit on 2018/1/16.
 */
@Configuration
public class PageHelperConfiguration {
    Log logger = getLogger(getClass());

    @Bean
    public PageHelper pageHelper() {
        String funcname = "注册PageHelper";

        PageHelper pageHelper = new PageHelper();
        Properties pageHelperConfig = new Properties();

        pageHelperConfig.setProperty("offsetAsPageNum", "true");
        pageHelperConfig.setProperty("rowBoundsWithCount", "true");
        pageHelperConfig.setProperty("reasonable", "true");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        pageHelperConfig.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(pageHelperConfig);

        logger.info(logstr(funcname));
        return pageHelper;
    }

}
