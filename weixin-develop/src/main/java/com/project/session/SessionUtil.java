package com.project.session;

import com.project.constant.WebConstant;
import com.project.model.sql.UserPrivilege;
import share.WebContext;
import share.WebSession;

import java.util.Map;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */
public class SessionUtil {
    /**
     * 获取当前用户的角色信息Map，保存的是频道号到list<roleid>的一个映射map
     * @param openid 属性key
     * @return 会话属性值
     */
    public static Map getCurrentUserPrivilegeinfo(String openid) {
        WebSession session = WebContext.getSession();
        Map obj = session.getAttribute(openid, Map.class);
        return obj;
    }

    /**
     * 获取当前用户
     * @param openid 属性key
     * @return 会话属性值
     */
    public static UserPrivilege getCurrentUser(String openid) {
        WebSession session = WebContext.getSession();
        UserPrivilege obj = session.getAttribute(openid, UserPrivilege.class);
        return obj;
    }

    /**
     * 设置当前用户
     * @param openid
     * @param value
     */
    public static void setCurrentUser(String openid, Object value) {
        WebSession session = WebContext.getSession();
        session.setAttribute(openid, value);
    }

    /**
     * 获取用户会话属性
     * @param openid 属性key
     * @return 会话属性值
     */
    public static Object getAttribute(String openid, Class<?> cls) {
        WebSession session = WebContext.getSession();
        return session.getAttribute(openid, cls);
    }

    /**
     * 获取用户会话属性
     * @param openid 属性key
     * @return 会话属性值
     */
    public static String getStringAttribute(String openid) {
        WebSession session = WebContext.getSession();
        return session.getAttribute(openid);
    }

    /**
     * 获取用户会话属性
     * @param openid 属性key
     * @return 会话属性值
     */
    public static String[] getStringArrayAttribute(String openid, Class<?> cls) {
        WebSession session = WebContext.getSession();
        Object obj = session.getAttribute(openid, cls);
        if(obj == null){
            return null;
        }
        return (String[])obj;
    }

    /**
     * 设置用户会话属性值
     * @param openid
     * @param value
     */
    public static void setAttribute(String openid, Object value) {
        WebSession session = WebContext.getSession();
        session.setAttribute(openid, value);
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
    public static String getOpenId(){
        WebSession session = WebContext.getSession();
        if(session == null){
            return "无sessionid";
        }
        UserPrivilege user = session.getAttribute(WebConstant.CURRENT_USER, UserPrivilege.class);
        if(user==null){
            return "未登录";
        }
        return user.getOpenid();
    }
}
