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
    private String listLink;
    private String cutImg;
    private String proxyUrl;

    public String getProxyUrl() {
        return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public String getCutImg() {
        return cutImg;
    }

    public void setCutImg(String cutImg) {
        this.cutImg = cutImg;
    }

    public String getListLink() {
        return listLink;
    }

    public void setListLink(String listLink) {
        this.listLink = listLink;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }
}
