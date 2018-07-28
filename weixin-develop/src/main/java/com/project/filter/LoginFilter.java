package com.project.filter;

import com.project.common.util.LogUtil;
import com.project.constant.WebConstant;
import com.project.model.sql.SystemUser;
import com.project.session.SessionUtil;
import org.apache.commons.logging.Log;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */

//@Component
//@Order(2)
//@WebFilter(urlPatterns = "/*",filterName = "loginFilter")
public class LoginFilter implements Filter {

    private Log logger = LogUtil.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String lgUrl = httpRequest.getServletPath();

        //here add urls that do not need login
        if(lgUrl.contains("/wechat/access")){
            chain.doFilter(httpRequest, httpResponse);
            return;
        }
        if(lgUrl.contains("/wechat/material")){
            chain.doFilter(httpRequest, httpResponse);
            return;
        }


//        logger.info("request recieved:"+lgUrl);
////        判断当前用户是否登录
//        SystemUser cu = SessionUtil.getCurrentUser(WebConstant.CURRENT_USER);
//        if (cu == null) {
//            if(lgUrl.equals("/system/login")){
//                chain.doFilter(httpRequest, httpResponse);
//            }else {
//                httpResponse.setCharacterEncoding("UTF-8");
//                httpResponse.setContentType("application/json; charset=utf-8");
//
//                String jsonStr = "{\"loginUrl\":\"" + "http://localhost:3006/#/login\"" + "}";
//                PrintWriter out = null;
//                try {
//                    out = httpResponse.getWriter();
//                    out.write(jsonStr);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (out != null) {
//                        out.close();
//                    }
//                }
//            }
//        } else {
//            logger.info("logined user,session id:"+SessionUtil.getSessionId());
//            chain.doFilter(httpRequest, httpResponse);
//        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}