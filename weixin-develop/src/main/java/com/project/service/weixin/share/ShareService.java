package com.project.service.weixin.share;

import com.project.common.util.LogUtil;
import com.project.common.util.MD5Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
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

    private Map<String,String> map = new HashMap<String,String>();

    public static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式

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
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(html);
        html = m_script.replaceAll(""); // 过滤script标签
        logger.debug("过滤后的：" + html);
        return html;
    }

    public String removeData_src(String html){
        return html.replace("data-src","src");
    }

    public String addProxyUrl(String html){
        //过滤Image 标签
        final String regEx_imgsrc = "<img [^>]*src=['\"]([^'\"]+)[^>]*>";
        html = replaceMathch(html, regEx_imgsrc);

        final String regEx_SectionUrl = "<section[^>]+url\\(&quot;([^)]+)&quot;\\)[^>]+>";
        html = replaceMathch(html, regEx_SectionUrl);
        return html;
    }

    public String replaceMathch(String html, String regEx_imgsrc) {
        Pattern p_imgsrc = Pattern.compile(regEx_imgsrc, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_imgsrc.matcher(html);
        while (m_script.find()){
            String url = m_script.group(1);
            String makeUpUrl="";
            if(!(url.startsWith("http:")||url.startsWith("https:"))){
                makeUpUrl = "http:" + url;
            }else{
                makeUpUrl = url;
            }
            logger.info(regEx_imgsrc + ",匹配到:" + makeUpUrl);
            String urlMd5Key = MD5Util.stringMD5(makeUpUrl);
            map.put(urlMd5Key,makeUpUrl);
            String proxyUrl = shareServiceProperty.getProxyUrl()+urlMd5Key;
            html= html.replace(url,proxyUrl);
        }
        return html;
    }

    public String getRealWechatUrl(String urlMd5Key){
        return map.get(urlMd5Key);
    }
}
