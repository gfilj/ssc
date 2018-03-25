package com.project.webmagic;

import com.project.common.util.LogUtil;
import com.project.webdriver.login.LoginService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

import javax.annotation.concurrent.ThreadSafe;

/**
 * 实现自定义的下载
 * Created by goforit on 2017/12/23.
 */
@Service
@ThreadSafe
public class PhantomjsDriverDownloader extends AbstractDownloader {

    @Autowired
    private LoginService loginService;

    private int threadNum;

    Log logger = LogUtil.getLogger(getClass());
    @Override
    public Page download(Request request, Task task) {
        logger.info("启动下载任务,下载链接：" + request.getUrl());
        Page page = new Page();
        page.setRawText(loginService.currentSource());
        page.setUrl(new PlainText(request.getUrl()));
        page.setRequest(request);
        page.setStatusCode(200);
        return page;
    }

    @Override
    public void setThread(int i) {
        threadNum = 1;
    }
}
