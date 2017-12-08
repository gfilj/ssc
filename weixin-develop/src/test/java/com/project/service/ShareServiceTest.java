package com.project.service;

import com.project.BaseTest;
import com.project.service.weixin.share.ShareService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/12/7.
 */
public class ShareServiceTest extends BaseTest {
    @Autowired
    private ShareService shareService;

    private String url = "http://mp.weixin.qq" +
            ".com/s?__biz=MzUzODAwNDA0Ng==&mid=100000182&idx=1&sn=5e573b621067a1ce73962abd67de7504&chksm=7adf1e974da89781e477bd3f2e6297f515c986945bf986f0f0c4c44d979b6e7b7a112993b530#rd";
    @Test
    public void testemoveScriptR(){
        shareService.removeScript(url);
    }
}
