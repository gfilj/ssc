package share;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import share.storage.StorageHandler;
import share.util.JSONUtil;

public class WebSession {
    private static final Logger logger = LoggerFactory.getLogger(WebSession.class);
   
    private String sessionId;
    private final StorageHandler sessionHandler;
    private final CookieHandler cookieHandler;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;
    private final Map<String,Object> cache = Collections.synchronizedMap(new HashMap<String,Object>());
    
    public WebSession(HttpServletRequest request,HttpServletResponse response,StorageHandler sessionHandler,CookieHandler cookieHandler){
        this.request = request;
        this.servletContext = request.getServletContext();
        this.response = response;
        this.sessionHandler = sessionHandler;
        this.cookieHandler = cookieHandler;
    }
    
    public WebSession(String sessionId,HttpServletRequest request,HttpServletResponse response,StorageHandler sessionHandler,CookieHandler cookieHandler){
        this.sessionId = sessionId;
        this.request = request;
        this.servletContext = request.getServletContext();
        this.response = response;
        this.sessionHandler = sessionHandler;
        this.cookieHandler = cookieHandler;
    }
    
    public void setAttribute(String name, Object value) {
        if(sessionId!=null){
        	cache.put(name, value);
            
            String valueJson = (value instanceof String) ? value.toString() : JSONUtil.toJson(value);
            sessionHandler.setAttribute(sessionId, name, valueJson);
        }
    }
    
    public String getAttribute(String name) {
    	if(sessionId==null){
            return null;
        }
        if(cache.containsKey(name)){
            logger.debug("SESSION CACHE hit key:"+name);
            return cache.get(name).toString();
        }
        
        String result = sessionHandler.getAttribute(sessionId, name);
        if(result!=null){
            cache.put(name, result);
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name,Class<T> c) {
        if(sessionId==null){
            return null;
        }
        if(cache.containsKey(name)){
            logger.debug("SESSION CACHE hit key:"+name);
            return (T) cache.get(name);
        }
        String resultStr = sessionHandler.getAttribute(sessionId, name);
        T result = JSONUtil.fromJson(resultStr,  c);;
        if(result!=null){
            cache.put(name, result);
        }
        return result;
    }
    
    public void removeSessionInCookie(){
        if(sessionId!=null){
            try {
                cookieHandler.removeSessionId(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void addSessionInCookie(){
        if(sessionId!=null){
            try {
                cookieHandler.setSessionId(this.sessionId,request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void removeAttribute(String name){
        if(sessionId!=null){
        	cache.remove(name);
            sessionHandler.removeAttribute(sessionId, name);
        }
    }
    
    public void setMaxInactiveInterval(int seconds){
        if(sessionId!=null){
            sessionHandler.setMaxInactiveInterval(sessionId, seconds);
        }
    }
    
    public void restMaxInactiveInterval(){
        if(sessionId!=null){
            sessionHandler.setMaxInactiveInterval(sessionId, -1);
        }
    }
    
    /**
     * 认证通过 调用一次 ，其他场景请勿调用
     * @return
     */
    public WebSession initWebSession(){
        if(sessionId!=null){
            return this;
        }
        this.sessionId = sessionHandler.createSessionId();
        if (sessionId == null) {
            throw new SessionException("create session id fail.");
        }
        return this;
    }
    
    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }
}
