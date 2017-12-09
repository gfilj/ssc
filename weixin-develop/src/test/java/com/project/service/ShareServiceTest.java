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

    private String imgHtml = "<img style=\"width:100%;vertical-align:middle;\" src=\"http://mmbiz.qpic" +
            ".cn/mmbiz_png/OoxQeTGnyxFxAa1iaTCqXtfn4VPdOfLEcnTzHyssqCIlkTm1gLmwvlAxGicNS3wnIg5cP4lniagEib2EEs2cTbsIPQ/0?wx_fmt=png\" data-width=\"100%\" class=\"\" data-ratio=\"0.9209039548022598\" data-w=\"177\" data-type=\"png\"><img style=\"width:100%;vertical-align:middle;\" src=\"http://www.kurkuma.cn/videoproxy/mmbiz.qpic.cn/mmbiz_png/OoxQeTGnyxFxAa1iaTCqXtfn4VPdOfLEcnTzHyssqCIlkTm1gLmwvlAxGicNS3wnIg5cP4lniagEib2EEs2cTbsIPQ/0?wx_fmt=png\" data-width=\"100%\" class=\"\" data-ratio=\"0.9209039548022598\" data-w=\"177\" data-type=\"png\"><img style=\"width:100%;vertical-align:middle;\" src=\"http://www.kurkuma.cn/videoproxy/mmbiz.qpic.cn/mmbiz_png/OoxQeTGnyxFxAa1iaTCqXtfn4VPdOfLEcnTzHyssqCIlkTm1gLmwvlAxGicNS3wnIg5cP4lniagEib2EEs2cTbsIPQ/0?wx_fmt=png\" data-width=\"100%\" class=\"\" data-ratio=\"0.9209039548022598\" data-w=\"177\" data-type=\"png\"><img style=\"width:100%;vertical-align:middle;\" src=\"http://www.kurkuma.cn/videoproxy/mmbiz.qpic.cn/mmbiz_png/OoxQeTGnyxFxAa1iaTCqXtfn4VPdOfLEcnTzHyssqCIlkTm1gLmwvlAxGicNS3wnIg5cP4lniagEib2EEs2cTbsIPQ/0?wx_fmt=png\" data-width=\"100%\" class=\"\" data-ratio=\"0.9209039548022598\" data-w=\"177\" data-type=\"png\"><img style=\"width:100%;vertical-align:middle;\" src=\"http://www.kurkuma.cn/videoproxy/mmbiz.qpic.cn/mmbiz_png/OoxQeTGnyxFxAa1iaTCqXtfn4VPdOfLEcnTzHyssqCIlkTm1gLmwvlAxGicNS3wnIg5cP4lniagEib2EEs2cTbsIPQ/0?wx_fmt=png\" data-width=\"100%\" class=\"\" data-ratio=\"0.9209039548022598\" data-w=\"177\" data-type=\"png\">";
    @Test
    public void testemoveScriptR(){
        shareService.removeScript(url);
    }
    @Test
    public void testAddProxy(){
        System.out.println(shareService.addProxyUrl(imgHtml));
    }
}
