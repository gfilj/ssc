package com.netease.fileupload.service.MD5;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/9/11.
 */
@Component
@ConfigurationProperties(prefix = "storage")
public class MD5Properties {
}
