/**
 * @(#)CookieUtil.java, 2016年4月12日. 
 * 
 * Copyright 2016 netease, Inc. All rights reserved.
 * Netease PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.project.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {

	/**
	 * 生成cookie
	 */
	public static void setCookie(HttpServletResponse response, String domain,
                                 String name, String value, int time) {
		if(value==null) value="";
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");                //设置Cookie适用的路径
		cookie.setDomain(domain);           //设置Cookie适用的域
		cookie.setMaxAge(time);             //设置Cookie有效时间
		response.addCookie(cookie);
        response.setHeader("P3P","CP=.");  //跨域
	}

	/**
	 * 获取Cookie
	 * @param cookieDomain 
	 */
	public static Cookie getCookie(HttpServletRequest request, String name, String cookieDomain) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
            for (Cookie cooky : cookies) {
                if(cooky != null){
                    if (cooky.getName().equals(name)) {
                        return cooky;
                    }
                }
            }
		}
		return null;
	}
	/**
	 * 获取Cookie的值
	 * @param cookieDomain 
	 */
	public static String getValue(HttpServletRequest request, String name, String cookieDomain) {
		Cookie cookie = getCookie(request, name,cookieDomain);
		if (cookie == null) return null;
		String value = cookie.getValue();
		if ("null".equals(value)) return null;
		return value;
	}
	
	/**
	 * 删除cookie
	 * @param request 
	 */
	public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response, String cookieDomain) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getDomain().equals(cookieDomain)) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	public static void deleteCookieByName(HttpServletRequest request,
                                          HttpServletResponse response, String cookieName, String cookieDomain) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
            for (Cookie cooky : cookies) {
                if(cooky != null && cooky.getName().equals(cookieName)){
                	 Cookie cookie = new Cookie(cooky.getName(),"");
                     cookie.setMaxAge(0);
                     cookie.setPath("/");
                     cookie.setDomain(cookieDomain);
                     response.addCookie(cookie);
                }
            }
		}
	}
}
