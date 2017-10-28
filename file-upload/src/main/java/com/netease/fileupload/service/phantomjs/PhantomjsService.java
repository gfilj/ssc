package com.netease.fileupload.service.phantomjs;

import com.netease.fileupload.model.User;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import com.netease.fileupload.util.WebdriverUtil;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/9/6.
 */
@Service
@ConfigurationProperties(prefix = "PhamtomjsService")
public class PhantomjsService implements InitializingBean {

    private String phantomjsPath;

    private Logger logger = LogUtil.getLogger(getClass());

    @Autowired
    private PrivilegeLoginService privilegeLoginService;

    @Autowired
    private UserTaskService userTaskService;

    PhantomJSDriver phantomjsDriver;
    public void execute(){
        if(phantomjsDriver !=null){
            return;
        }
        try {
            phantomjsDriver = WebdriverUtil.getPhantomjsDriver(phantomjsPath);
            logger.info(Constant.INFOStr("PhamtomjsService Init"));
            while (true){
                User user = userTaskService.waitNewUser();
                if(user!=null){
                    //del cookie
                    phantomjsDriver.manage().deleteAllCookies();
                    //执行登录
                    boolean result = privilegeLoginService.execute(phantomjsDriver, user);
                    user.singnalFinish();
                    if(result){
                        logger.info(Constant.INFOStr("selectProject"));
                        privilegeLoginService.selectProject(phantomjsDriver);
                    }
                }
            }

        }catch (Throwable e ){
            logger.error(Constant.ERRORStr("execute error"),e);
        }finally {
            logger.info("phantomjsDriver close");
            phantomjsDriver.quit();
        }

    }

    public String getPhantomjsPath() {
        return phantomjsPath;
    }

    public void setPhantomjsPath(String phantomjsPath) {
        this.phantomjsPath = phantomjsPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                execute();
//            }
//        }).start();
    }
}
