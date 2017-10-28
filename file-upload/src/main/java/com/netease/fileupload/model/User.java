package com.netease.fileupload.model;

import com.netease.fileupload.model.builder.ResultBuilder;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;

import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by goforit on 2017/9/6.
 */
public class User {

    private String username;
    private String password;
    private Set<Cookie> cookies;

    private static Logger logger = LogUtil.getLogger(User.class);

    private ReentrantLock finishLock = new ReentrantLock();

    private Condition finishLockCondition = finishLock.newCondition();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(Set<Cookie> cookies) {
        this.cookies = cookies;
    }

    public void waitFinish() {
        try {
            finishLock.lock();
            finishLockCondition.await();
        } catch (InterruptedException e) {
            logger.error(Constant.ERRORStr(username + " waitFinish - interrupted, error"), e);
        } finally {
            finishLock.unlock();
        }
    }

    public void singnalFinish(){
        try {
            finishLock.lock();
            finishLockCondition.signalAll();
        } finally {
            finishLock.unlock();
        }
    }

    public Result getResult(){
        if(cookies.size() == 0){
            return ResultBuilder.getInstance().setInfo(Constant.HTTP_INFO_ERROR).setStatus(Constant.HTTP_STATUS_ERROR).setData(cookies).build();
        }else{
            return ResultBuilder.getInstance().setInfo(Constant.HTTP_INFO_OK).setStatus(Constant.HTTP_STATUS_OK).setData(cookies).build();
        }
    }

    public String simpleInfo() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
