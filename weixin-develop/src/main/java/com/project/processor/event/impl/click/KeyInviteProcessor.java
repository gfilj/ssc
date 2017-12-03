package com.project.processor.event.impl.click;

import com.project.common.exception.BusinessException;
import com.project.model.message.response.Article;
import com.project.model.message.response.NewsMessage;
import com.project.processor.event.EventProcessor;
import com.project.processor.event.impl.AbstractEventProcessor;
import com.project.service.message.util.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2017/12/3.
 */
@Service("KEY_INVITEProcessor")
public class KeyInviteProcessor extends AbstractEventProcessor implements EventProcessor {

    @Override
    public String doProcess(Map<String, String> map) throws BusinessException {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID

        logger.info("==============这是 CLICK 点击邀请码 事件!");
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
        article.setTitle("二维码");  //图文消息标题
        article.setUrl(showQrUrl);  //图文 url 链接
        List<Article> list = new ArrayList<Article>();
        list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里 list 中加入多个 Article 即可！
        newmsg.setArticleCount(list.size());
        newmsg.setArticles(list);
        return MessageUtil.newsMessageToXml(newmsg);
    }
}

