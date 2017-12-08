package com.project.service.weixin.share;

import com.project.common.util.LogUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by goforit on 2017/12/2.
 */
@Service
public class ShareService {

    @Autowired
    private ShareServiceProperty shareServiceProperty;
    private Logger logger = LogUtil.getLogger(ShareService.class);

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

    public String removeScript(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String html = doc.html();
        logger.debug("过滤前的：" + html);
        final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(html);
        html = m_script.replaceAll(""); // 过滤script标签
        logger.debug("过滤后的：" + html);
        return html;
    }

    public String removeData_src(String html){
        return html.replace("data-src","src");
    }
}
