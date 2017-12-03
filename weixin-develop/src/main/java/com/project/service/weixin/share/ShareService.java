package com.project.service.weixin.share;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by goforit on 2017/12/2.
 */
@Service
public class ShareService {

    @Autowired
    private ShareServiceProperty shareServiceProperty;

    public String createShareLink(String url, String imgUrl){
        try {
            String encodeUrl = URLEncoder.encode(url,"utf-8");
            return String.format(shareServiceProperty.getShareLink(),encodeUrl,imgUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return shareServiceProperty.getShareLink();
    }

    public String createListLink(String qrCode){
        try {
            String encodeUrl = URLEncoder.encode(qrCode,"utf-8");
            return String.format(shareServiceProperty.getListLink(),encodeUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return shareServiceProperty.getListLink();
    }
}
