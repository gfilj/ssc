package com.project.service.weixin.share;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/12/2.
 */
@Component
@ConfigurationProperties(prefix = "ShareServiceProperty")
public class ShareServiceProperty {

    private String shareLink;

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }
}
