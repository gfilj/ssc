package com.project.model.message.request;

/**
 * 文本消息
 * Created by goforit on 2017/10/29.
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
