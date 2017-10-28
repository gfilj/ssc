package com.netease.fileupload.service.phantomjs;

import com.netease.fileupload.model.User;
import com.netease.fileupload.util.LogUtil;
import com.netease.fileupload.util.WebdriverUtil;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/9/6.
 */
@Service
@ConfigurationProperties(prefix = "PrivilegeLoginService")
public class PrivilegeLoginService  implements InitializingBean {

    private String url;
    private String corpid;
    private String corppw;
    private String corpbtn;
    private String loginSuccess;
    private String projectUrl;

    private Logger logger = LogUtil.getLogger(getClass());

    public boolean execute(PhantomJSDriver phantomJSDriver, User user){

        WebdriverUtil.open(phantomJSDriver,url);
        WebdriverUtil.waitForCondition(phantomJSDriver,corpid);
        WebdriverUtil.jsSendKey(phantomJSDriver,corpid,user.getUsername());
        WebdriverUtil.jsSendKey(phantomJSDriver,corppw,user.getPassword());
        WebdriverUtil.btnClick(phantomJSDriver,corpbtn);
        user.setCookies(WebdriverUtil.cookiePrint(phantomJSDriver));
        return user.getCookies().size() != 0;
    }

    public void selectProject(PhantomJSDriver phantomJSDriver){
        WebdriverUtil.waitForCondition(phantomJSDriver,loginSuccess);
        WebdriverUtil.open(phantomJSDriver,String.format(projectUrl,System.currentTimeMillis()));
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getCorppw() {
        return corppw;
    }

    public void setCorppw(String corppw) {
        this.corppw = corppw;
    }

    public String getCorpbtn() {
        return corpbtn;
    }

    public void setCorpbtn(String corpbtn) {
        this.corpbtn = corpbtn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    @Override
    public String toString() {
        return "PrivilegeLoginService{" +
                "url='" + url + '\'' +
                ", corpid='" + corpid + '\'' +
                ", corppw='" + corppw + '\'' +
                ", corpbtn='" + corpbtn + '\'' +
                ", loginSuccess='" + loginSuccess + '\'' +
                ", projectUrl='" + projectUrl + '\'' +
                '}';
    }

    public String getLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(String loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        logger.info(Constant.INFOStr("PrivilegeLoginService Init") + this);
    }
}
