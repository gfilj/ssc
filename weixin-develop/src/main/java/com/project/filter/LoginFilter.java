package com.project.filter;

import com.project.common.util.LogUtil;
import com.project.constant.WebConstant;
import com.project.model.sql.UserPrivilege;
import com.project.session.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */
public class LoginFilter implements Filter {

    private Log logger = LogUtil.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String lgUrl = httpRequest.getServletPath();

        //here add urls that do not need login
        if(lgUrl.contains("/openapi/")){
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        logger.info("login filtering");
        //判断当前用户是否登录
//        UserPrivilege cu = SessionUtil.getCurrentUser(WebConstant.CURRENT_USER);
//        if (cu == null) {
//            String sessionToken = request.getParameter(ProjectConfig.AuthzConfig.PRIVILEGE_AUTHC_TOKEN);    //判断是否是权限系统发的请求
//            if (StringUtils.isBlank(sessionToken)) {
//                //二次验证用户是否登录
//
//                httpResponse.setCharacterEncoding("UTF-8");
//                httpResponse.setContentType("application/json; charset=utf-8");
//                String jsonStr = "{\"loginUrl\":\"" + ProjectConfig.AuthzConfig.loginUrl + "\",\"resultcode\":" + CommonStatusCode.StatusCode.NEED_LOGIN + "}";
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
//            } else {
//                httpRequest.getRequestDispatcher("/login").forward(httpRequest, httpResponse);
//            }
//        } else {
//            if (lgUrl.equals("/login")) {
//                // 跳转到获取频道的接口
//                httpResponse.sendRedirect("/index.html");
//            } else {
//                chain.doFilter(httpRequest, httpResponse);
//            }
//        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}