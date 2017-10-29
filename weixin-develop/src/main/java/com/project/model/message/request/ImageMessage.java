package com.project.model.message.request;

/**
 * 图片消息
 * Created by goforit on 2017/10/29.
 */
public class ImageMessage extends BaseMessage{
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
