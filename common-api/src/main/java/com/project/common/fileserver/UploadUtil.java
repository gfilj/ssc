package com.project.common.fileserver;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;

/**
 * Created by goforit on 2018/5/7.
 */
public class UploadUtil {

    public static String upload(String url, File imagefile){
        if(url == null || imagefile == null){
            return null;
        }
        PostMethod method = new PostMethod(url);
        try {
            // 重试
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(3, false));
            // 5秒超时连接
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
            Part[] parts = {new FilePart("file",imagefile)};
            method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(
                    5000);
            int statusCode = client.executeMethod(method);
            if (statusCode == HttpStatus.SC_OK) {
                JSONObject jsonObject = JSONObject.parseObject(method.getResponseBodyAsString());
                return jsonObject.getString("url");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return null;
    }
}
