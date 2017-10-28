package com.project.common.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.common.util.LogUtil;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.*;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 网络工具类
 * Created by goforit on 2017/10/22.
 */
public class NetUtil {


    public static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)";

    public static Logger logger = LogUtil.getLogger(NetUtil.class);

    /**
     * 获取单例的httpclient对象.
     *
     * @return
     */
    public static HttpClient getHttpClient() {
        return HttpClientHolder.httpclient;
    }

    private static class HttpClientHolder {

        private static HttpClient httpclient = initDefaultHttpClient();
    }

    //连接池空闲连接超时时间
    private static final long CONNECTION_POOL_IDLE_TIMEOUT = 20L;

    /**
     * 获取默认HTTP连接参数.
     *
     * @return
     */
    private static HttpParams initDefaultHttpParams() {
        int connectionTimeout = 10 * 1000;
        int socketTimetout = 10 * 1000;
        String userAgent = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)";
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout);
        HttpConnectionParams.setSoTimeout(httpParams, socketTimetout);
        HttpProtocolParams.setUserAgent(httpParams, userAgent);
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        return httpParams;
    }

    /**
     * 初始化HTTPCLIENT.
     *
     * @return
     */
    public static HttpClient initDefaultHttpClient() {
        ClientConnectionManager connManager = getDefaultClientConnectionManager();
        HttpParams httpParams = initDefaultHttpParams();
        DefaultHttpClient httpclient = new DefaultHttpClient(connManager, httpParams);
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            throw new RuntimeException("初始化 httpClientUtil异常", e);
        }
        SSLSocketFactory sf = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme httpScheme = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());
        Scheme httpsScheme = new Scheme("https", 443, sf);
        httpclient.getConnectionManager().getSchemeRegistry().register(httpScheme);
        httpclient.getConnectionManager().getSchemeRegistry().register(httpsScheme);
        enableGZIP(httpclient);
        enableRetryHandler(httpclient);
        enableKeepAliveStrategy(httpclient);
        return httpclient;
    }

    /**
     * 获取默认连接管理器.
     *
     * @return
     */
    private static ClientConnectionManager getDefaultClientConnectionManager() {
        int maxConnection = 10000;
        int maxConnettionPerRoute = 20;
        PoolingClientConnectionManager poolingClientConnectionManager = new PoolingClientConnectionManager();
        poolingClientConnectionManager.setMaxTotal(maxConnection);
        poolingClientConnectionManager.setDefaultMaxPerRoute(maxConnettionPerRoute);
        poolingClientConnectionManager.closeIdleConnections(CONNECTION_POOL_IDLE_TIMEOUT, TimeUnit.MINUTES);
        return poolingClientConnectionManager;
    }

    /**
     * 自动重连机制可用.
     *
     * @param httpClient
     */
    private static void enableRetryHandler(DefaultHttpClient httpClient) {
        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                boolean rtn = true;
                if (executionCount > 2) { //重连3次之后 不再重试
                    rtn = false;
                } else if (exception instanceof NoHttpResponseException || exception instanceof SocketTimeoutException
                        || exception instanceof ConnectTimeoutException) {
                    rtn = true; //只针对特定的异常重试
                } else {
                    HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
                    boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                    rtn = idempotent; // 只针对幂等的HTTP方法(GET HEAD)重试
                }
                return rtn;
            }
        };
        httpClient.setHttpRequestRetryHandler(myRetryHandler);
    }

    /**
     * 采用GZIP压缩方式.
     *
     * @param httpClient
     */
    private static void enableGZIP(DefaultHttpClient httpClient) {
        //GZIP传输
        httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            @Override
            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                if (!request.containsHeader("Accept-Encoding")) {
                    request.addHeader("Accept-Encoding", "gzip");
                }
            }
        });
        httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
            @Override
            public void process(final HttpResponse response, final HttpContext context) throws HttpException,
                    IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header ceheader = entity.getContentEncoding();
                    if (ceheader != null) {
                        HeaderElement[] codecs = ceheader.getElements();
                        for (int i = 0; i < codecs.length; i++) {
                            if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 持久连接可用.
     *
     * @param httpClient
     */
    private static void enableKeepAliveStrategy(DefaultHttpClient httpClient) {
        //持久连接策略
        httpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator iterator = new BasicHeaderElementIterator(response
                        .headerIterator(HTTP.CONN_KEEP_ALIVE)); // 兑现Header里的“keep-alive” （HTTP1.1中）
                while (iterator.hasNext()) {
                    HeaderElement header = iterator.nextElement();
                    String param = header.getName();
                    String value = header.getValue();
                    if (value != null && "timeout".equalsIgnoreCase(param)) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (Exception e) {
                            return 30 * 1000;
                        }
                    }
                }
                return 30 * 1000;
            }
        });
    }

    public static String readURL(String urladdress) {
        try {
            return readURL(urladdress, "gbk");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readURL(String urladdress, String encode) {
        try {
            return getContentByUrl(urladdress, encode);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean download(String urladdress, String filename) {
        try {
            if (filename.indexOf("/") == -1) {
                return false;
            }
            File pathfile = new File(filename.substring(0, filename.lastIndexOf("/")));
            pathfile.mkdirs();
            FileOutputStream out = new FileOutputStream(filename);
            try {
                byte[] result = getContent(urladdress);
                out.write(result);
                out.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取http网络内容.
     *
     * @param address         http网络地址
     * @param isPost          http的请求方式
     * @param requestCharset  http请求的编码
     * @param responseCharset http响应的编码
     * @return 网络响应内容
     * @throws Exception
     */
    public static String getHttpContent(String address, Map<String, String> params, boolean isPost, String requestCharset,
                                        String responseCharset) throws Exception {
        if (isPost) {
            return postContent(address, params, responseCharset);
        } else {
            StringBuilder paramString = new StringBuilder(20);
            if (params != null) {
                Set<String> set = params.keySet();
                String[] keys = new String[set.size()];
                set.toArray(keys);
                for (int i = 0; i < keys.length; i++) {
                    if (i != 0) {
                        paramString.append("&");
                    }
                    paramString.append(keys[i]).append("=").append(params.get(keys[i]));
                }
            }
            if (!StringUtils.isEmpty(paramString.toString())) {
                address += "?" + paramString.toString();
            }
            return getContentByUrl(address, responseCharset);
        }
    }

    /**
     * @param address
     * @param json
     * @return
     * @throws Exception
     */
    public static String getHttpContent(String address, String json, String requestCharset, String responseCharset) throws Exception {
        HttpPost httppost = new HttpPost(address);
        try {
            httppost.setEntity(new ByteArrayEntity(json.getBytes(requestCharset)));
            HttpResponse response = getHttpClient().execute(httppost);
            return EntityUtils.toString(response.getEntity(), responseCharset);
        } catch (IOException e) {
            throw e;
        } finally {
            httppost.abort();
        }
    }

    /**
     * 获取http网络内容.
     *
     * @param address         http网络地址
     * @param requestCharset  http请求的编码
     * @param responseCharset http响应的编码
     * @return 网络响应内容
     * @throws Exception
     */
    public static String getHttpContentByDelete(String address, Map<String, String> params, String requestCharset,
                                                String responseCharset) throws Exception {
        StringBuilder paramString = new StringBuilder(20);
        if (params != null) {
            Set<String> set = params.keySet();
            String[] keys = new String[set.size()];
            set.toArray(keys);
            for (int i = 0; i < keys.length; i++) {
                if (i != 0) {
                    paramString.append("&");
                }
                paramString.append(keys[i]).append("=").append(params.get(keys[i]));
            }
        }
        if (!StringUtils.isEmpty(paramString.toString())) {
            address += "?" + paramString.toString();
        }
        HttpDelete httpDelete = new HttpDelete(address);
        try {
            HttpResponse httpResponse = getHttpClient().execute(httpDelete);
            return EntityUtils.toString(httpResponse.getEntity(), responseCharset);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpDelete.abort();
        }
        return "";
    }

    /**
     * post 数据到指定地址，并获取返回结果.
     *
     * @throws IOException
     */
    public static String postContent(String url, Map<String, String> params, String encoding) throws IOException {
        HttpPost httppost = new HttpPost(url);
        try {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            Set<String> keys = params.keySet();
            for (String key : keys) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            httppost.setHeader("Referer", url);
            httppost.setHeader("Connection", "close");
            httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            httppost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
            HttpResponse response = getHttpClient().execute(httppost);
            return EntityUtils.toString(response.getEntity(), encoding);
        } catch (IOException e) {
            throw e;
        } finally {
            httppost.abort();
        }
    }

    /**
     * 获取指定host和地址的内容.
     *
     * @param url      指定链接
     * @param host     指定的host
     * @param encoding 编码
     * @return
     */
    public static String getContentByUrlAndHost(String url, String host, String encoding) {
        HttpGet httpGet = new HttpGet(url);
        if (!StringUtils.isEmpty(host)) {
            httpGet.setHeader("Host", host);
        }
        try {
            HttpResponse httpResponse = getHttpClient().execute(httpGet);
            return EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.abort();
        }
        return "";
    }

    /**
     * 通过指定Token获取指定host和地址的内容.
     *
     * @param url      指定链接
     * @param host     指定的host
     * @param encoding 编码
     * @param apiToken Token
     * @return
     */
    public static String getContentByUrlAndHostAndToken(String url, String host, String encoding, String apiToken) {
        HttpGet httpGet = new HttpGet(url);
        if (!StringUtils.isEmpty(host)) {
            httpGet.setHeader("Host", host);
        }
        if (!StringUtils.isEmpty(apiToken)) {
            httpGet.setHeader("apiToken", apiToken);
        }
        try {
            HttpResponse httpResponse = getHttpClient().execute(httpGet);
            return EntityUtils.toString(httpResponse.getEntity(), encoding);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.abort();
        }
        return "";
    }

    /**
     * 严格读取页面内容，除200外的返回结果全部舍弃.
     *
     * @param url
     * @param encoding
     * @return
     */
    public static String getStrictContent(String url, String encoding) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", USER_AGENT);
        try {
            HttpResponse httpResponse = getHttpClient().execute(httpGet);
            //读取响应数据
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                return EntityUtils.toString(httpResponse.getEntity(), encoding);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.abort();
        }
        return "";
    }

    /**
     * 读取网络地址，返回原始字节型数据.
     *
     * @param url 网络地址
     * @return
     * @throws IOException
     */
    public static byte[] getContent(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        try {
            httpGet.setHeader("Referer", url);
            httpGet.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            HttpResponse response = getHttpClient().execute(httpGet);
            return EntityUtils.toByteArray(response.getEntity());
        } catch (IOException e) {
            throw e;
        } finally {
            httpGet.abort();
        }
    }

    public static String getContentByUrl(String url, String encoding) {
        return getContentByUrlAndHost(url, null, encoding);
    }

    public static String postContentByUrl(String url, Map<String, String> params, String encoding) {
        try {
            return postContent(url, params, encoding);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /****
     * 处理参数需要传数组的情况
     * @param url
     * @param keys
     * @param values
     * @param encoding
     * @return
     */
    public static String postContentWithSameParam(String url, List<String> keys, List<String> values, String encoding) {
        HttpPost httppost = new HttpPost(url);
        try {
            if (keys.size() > values.size()) {
                throw new IndexOutOfBoundsException("keys size is bigger than values size. " + keys.size() + " > "
                        + values.size());
            }
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (int i = 0; i < keys.size(); i++) {
                nvps.add(new BasicNameValuePair(keys.get(i), values.get(i)));
            }
            httppost.setHeader("Referer", url);
            httppost.setHeader("Connection", "close");
            httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            httppost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
            HttpResponse response = getHttpClient().execute(httppost);
            return EntityUtils.toString(response.getEntity(), encoding);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httppost.abort();
        }
        return null;
    }

    /**
     * post发送xml流 数据到指定地址，并获取返回结果.
     *
     * @throws IOException
     */
    public static String postXmlData(String url, String xml, String encoding) throws IOException {
        HttpPost httppost = new HttpPost(url);
        try {
            StringEntity se = new StringEntity(xml, encoding);
            httppost.setEntity(se);
            httppost.setHeader("Referer", url);
            httppost.setHeader("Connection", "close");
            httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            HttpResponse response = getHttpClient().execute(httppost);
            return EntityUtils.toString(response.getEntity(), encoding);
        } catch (IOException e) {
            throw e;
        } finally {
            httppost.abort();
        }
    }

}
