package com.project.filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import share.WebContext;
import share.WebSession;
import share.WebSessionManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * $myDescription
 * Create by Fenix_Bao on 2018/4/1.
 */
public class WebContextFilter implements Filter {

    private ServletContext ctx;
    private WebApplicationContext wac;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.ctx = config.getServletContext();
        this.wac = WebApplicationContextUtils.getWebApplicationContext(this.ctx);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse =  (HttpServletResponse) res;
        //白名单
//        Set<String> urlSet = WhiteListFilter.getWhiltListConfig();
//        if (urlSet.contains(httpRequest.getServletPath())) {
//            chain.doFilter(req, res);
//            return;
//        }

        try {
            WebSessionManager sessionManager = (WebSessionManager) this.wac.getBean(WebSessionManager.class);
            WebSession session = sessionManager.getSession(httpRequest,httpResponse);
            session.restMaxInactiveInterval();
            WebContext.set(session);
            chain.doFilter(req, res);
        } finally {
            WebContext.set(null);
        }
    }

    @Override
    public void destroy() {

    }

}
