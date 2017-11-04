package com.project.dispatcher;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.message.response.Article;
import com.project.model.message.response.NewsMessage;
import com.project.service.message.util.MessageUtil;
import com.project.service.weixin.WechatAccessService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2017/10/29.
 */
@Service
public class MsgDispatcher {

    private Logger logger = LogUtil.getLogger(MsgDispatcher.class);

    public static final String qrCodeMessage = "二维码";

    @Autowired
    private WechatAccessService wechatAccessService;

    public String processMessage(Map<String, String> map) throws BusinessException {
        String openid = map.get("FromUserName"); //用户 openid
        logger.info("测试获取个人信息接口：" + wechatAccessService.getUserInfo(openid));
        String mpid = map.get("ToUserName");   //公众号原始 ID
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            String content = map.get("Content").trim();
            logger.info("接受到文本消息：" + content);
            if(qrCodeMessage.equals(content)){
                //返回二维码图片
                String showQrUrl = wechatAccessService.generateShowRRUrl(openid);
                logger.info("获取二维码展示url ："+showQrUrl);
                NewsMessage newmsg = new NewsMessage();
                newmsg.setToUserName(openid);
                newmsg.setFromUserName(mpid);
                newmsg.setCreateTime(new Date().getTime());
                newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

                Article article = new Article();
                article.setDescription("绑定绑定您个人消息的二维码"); //图文消息的描述
                article.setPicUrl(showQrUrl); //图文消息图片地址
                article.setTitle("测试消息");  //图文消息标题
                article.setUrl("http://www.cuiyongzhi.com");  //图文 url 链接
                List<Article> list = new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageToXml(newmsg);
            }
//            //普通文本消息
//            TextMessage txtmsg = new TextMessage();
//            txtmsg.setToUserName(openid);
//            txtmsg.setFromUserName(mpid);
//            txtmsg.setCreateTime(new Date().getTime()/1000);
//            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//
//            txtmsg.setContent("你好，这里是goforit测试账号！");
//            return MessageUtil.textMessageToXml(txtmsg);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            logger.info("这是图片消息");
            NewsMessage newmsg = new NewsMessage();
            newmsg.setToUserName(openid);
            newmsg.setFromUserName(mpid);
            newmsg.setCreateTime(new Date().getTime());
            newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

            Article article = new Article();
            article.setDescription("这是图文消息 1"); //图文消息的描述
            article.setPicUrl("http://res.cuiyongzhi.com/2016/03/201603086749_6850.png"); //图文消息图片地址
            article.setTitle("图文消息 1");  //图文消息标题
            article.setUrl("http://www.cuiyongzhi.com");  //图文 url 链接
            List<Article> list = new ArrayList<Article>();
            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
            newmsg.setArticleCount(list.size());
            newmsg.setArticles(list);
            return MessageUtil.newsMessageToXml(newmsg);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            logger.info("这是链接消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            logger.info("这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            logger.info("这是视频消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            logger.info("这是语音消息！");
        }

        return "success";
    }
}
