package com.netease.fileupload.service.project;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/9/18.
 */
@Service
@ConfigurationProperties(prefix = "ProjectInfo")
public class ProjectInfo {

    private String projectToken;
    private String projectKey;
    private String authApi;

    public String getProjectToken() {
        return projectToken;
    }

    public void setProjectToken(String projectToken) {
        this.projectToken = projectToken;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public String getAuthApi() {
        return authApi;
    }

    public void setAuthApi(String authApi) {
        this.authApi = authApi;
    }
}
