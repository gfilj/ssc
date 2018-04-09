package com.project.session;

import com.project.constant.WebConstant;
import com.project.model.sql.SystemUser;
import share.WebContext;
import share.WebSession;

import java.util.Map;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */
public class SessionUtil {
    /**
     * 获取当前用户的角色信息Map，保存的是频道号到list<roleid>的一个映射map
     * @param username 属性key
     * @return 会话属性值
     */
    public static Map getCurrentUserPrivilegeinfo(String username) {
        WebSession session = WebContext.getSession();
        Map obj = session.getAttribute(username, Map.class);
        return obj;
    }

    /**
     * 获取当前用户
     * @param username 属性key
     * @return 会话属性值
     */
    public static SystemUser getCurrentUser(String username) {
        WebSession session = WebContext.getSession();
        SystemUser obj = session.getAttribute(username, SystemUser.class);
        return obj;
    }

    /**
     * 设置当前用户
     * @param username
     * @param value
     */
    public static void setCurrentUser(String username, Object value) {
        WebSession session = WebContext.getSession();
        session.setAttribute(username, value);
    }

    /**
     * 获取用户会话属性
     * @param username 属性key
     * @return 会话属性值
     */
    public static Object getAttribute(String username, Class<?> cls) {
        WebSession session = WebContext.getSession();
        return session.getAttribute(username, cls);
    }

    /**
     * 获取用户会话属性
     * @param username 属性key
     * @return 会话属性值
     */
    public static String getStringAttribute(String username) {
        WebSession session = WebContext.getSession();
        return session.getAttribute(username);
    }

    /**
     * 获取用户会话属性
     * @param username 属性key
     * @return 会话属性值
     */
    public static String[] getStringArrayAttribute(String username, Class<?> cls) {
        WebSession session = WebContext.getSession();
        Object obj = session.getAttribute(username, cls);
        if(obj == null){
            return null;
        }
        return (String[])obj;
    }

    /**
     * 设置用户会话属性值
     * @param username
     * @param value
     */
    public static void setAttribute(String username, Object value) {
        WebSession session = WebContext.getSession();
        session.setAttribute(username, value);
    }

    /**
     * 获取当前的sessionId
     * @return
     */
    public static String getSessionId(){
        WebSession session = WebContext.getSession();
        if(session == null){
            return "无sessionid";
        }
        return session.getSessionId();
    }
    /**
     * 获取当前user's openid
     */
    public static String getUsername(){
        WebSession session = WebContext.getSession();
        if(session == null){
            return "无sessionid";
        }
        SystemUser user = session.getAttribute(WebConstant.CURRENT_USER, SystemUser.class);
        if(user==null){
            return "未登录";
        }
        return user.getUsername();
    }
}
