package com.project.controller.wechat.login;

import com.project.common.util.LogUtil;
import com.project.service.login.LoginService;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import share.WebContext;
import share.WebSession;

import javax.annotation.Resource;
import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Create by Fenix_Bao on 2018/3/31.
 */
@Controller
public class LoginController {
    //用户信息数据有效时间
    public static final int USER_CACHE_TIME_OUT_SECONDS = 12 * 60 * 60;

    private Log logger = LogUtil.getLogger(getClass());

    @Resource
    private LoginService loginService;

    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(defaultValue="/index.html") String redirectUrl) {

//        String sessionToken = request.getParameter(ProjectConfig.AuthzConfig.PRIVILEGE_AUTHC_TOKEN);
//
//        String content = authzUserAgain(sessionToken);
//        if (content == null) {
//            //网络连接出现异常
//            logger.error("网络连接异常");
//        }
//
//        AuthzParam ap = parseAuthzData(content);
//        if (ap == null) {
//            //认证失败
//            logger.error("网络连接异常");
//        }
//        ap.setSessionToken(sessionToken);
        WebSession session = WebContext.getSession().initWebSession();
        //AuthzParamUtil.setAuthzParam(ap);

//        UserInfoVO userInfoVO = new UserInfoVO();
//        userInfoVO.setUserid(ap.getAccount().trim());
//        userInfoVO.setBuloname(ap.getEmail());
//        userInfoVO.setRealname(ap.getName());
//        userInfoVO.setBuloid(ap.getCard());
//        UserInfoVO user = null;
//        try {
//            user = loginService.login(userInfoVO);
//        } catch (BusinessException e) {
//            quitAuthzUser(sessionToken);
//            logger.error("登录系统时出现异常," + e.getMessage());
//            return "redirect:error.html?errorCode=" + e.getErrorCode();//失败时需要指定跳转地址
//        }catch (Exception e) {
//            quitAuthzUser(sessionToken);
//            logger.error("登录系统时出现异常," + e.getMessage());
//            return "redirect:error.html?errorCode=" + CommonStatusCode.StatusCode.USER_LOGIN_FAILED;//失败时需要指定跳转地址
//        }

//        logger.info(userInfoVO.getUserid() +  " login");
//
//        session.setAttribute(WebConstant.CURRENT_USER, user);
//        session.setAttribute(WebConstant.CURRENT_USER_AUTH_ID,ap.getId());
//
//        session.setMaxInactiveInterval(USER_CACHE_TIME_OUT_SECONDS);

        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value="/quit")
    @ResponseBody
    public WebResult quit(HttpServletRequest request, HttpServletResponse response) {
        WebResult webResult = null;
//        Map<String,Object> map = new HashMap<String,Object>();
//        String sessionToken = CookieUtil.getValue(request,
//                ProjectConfig.AuthzConfig.COOkIE_SESSION_SID,ProjectConfig.cookieDomain);
//        if(quitAuthzUser(sessionToken)){
//            UserInfoVO userInfoVO = UserUtil.getCurrentUserInfo();
//            this.loginService.loginOut(userInfoVO);
//            SessionUtil.setAttribute(WebConstant.CURRENT_USER,null);
//            SessionUtil.setAttribute(WebConstant.CURRENT_USER_PRIVILEGE_INFO,null);
//            CookieUtil.deleteCookie(request, response, ProjectConfig.cookieDomain);
//            CookieUtil.deleteCookieByName(request,response,ProjectConfig.AuthzConfig.COOkIE_SESSION_SID,
//                    ProjectConfig.AuthzConfig.COOkIE_DOMAIN);
//            WebSession session = WebContext.getSession();
//            session.removeSessionInCookie();
//            map.put("status", true);
//            map.put("loginUrl", ProjectConfig.AuthzConfig.loginUrl);
//        }else{
//            map.put("status", false);
//        }
//        webResult = WebResult.ResultFactory.makeOKResult(map);
        return webResult;
    }

    /**
     * 退出用户
     * @param sessionToken
     */
    private boolean quitAuthzUser(String sessionToken) {
        boolean success = false;
//        if(StringUtil.isFine(sessionToken)){
//            Map<String,String> params = new HashMap<>();
//            params.put("projectToken", ProjectConfig.AuthzConfig.PROJECT_TOKEN);
//            params.put("sessionToken", sessionToken);
//            logger.info("sessionToken:" + sessionToken);
//            String content = null;
//            try {
//                content = NetUtil.deleteHttpContent(ProjectConfig.AuthzConfig.AUTH_QUIT_URL, params, false, "UTF-8", "UTF-8");
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//            }
//            logger.info("content={}",content);
//            // 权限系统新增状态码8203，代表权限系统的该sessionToken已经被销毁，可能是其他业务系统已经退出
//            if(content != null && (content.contains("8202") || content.contains("8203"))){
//                success = true;
//            } else if (content != null) {
//                logger.info("调用权限系统销毁登陆session失败，参数sessionToken=" + sessionToken);
//            }
//        }
        return success;
    }

    private String authzUserAgain(String sessionToken) {
//        //认证
//        Map<String,String> params = new HashMap<>();
//        params.put("projectToken", ProjectConfig.AuthzConfig.PROJECT_TOKEN);
//        params.put("sessionToken", sessionToken);
//
        String content = null;
//        try {
//            content = NetUtil.getHttpContent(ProjectConfig.AuthzConfig.AUTH_CHECK_URL, params, false, "UTF-8", "UTF-8");
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
        return content;
    }

//    @SuppressWarnings("unchecked")
//    private AuthzParam parseAuthzData(String content) {
//
//        AuthzParam au = null;
//        Map<String,Object> result = JSONUtil.fromJson(content, Map.class);
//        Integer code = (Integer) result.get("resultcode");
//        if (ProjectConfig.AuthzConfig.AUTH_SUCESS.equals(code)) {
//            Map<String,Object> userMap = (Map<String,Object>) result.get("data");
//            Integer auId = Integer.parseInt(userMap.get("id").toString());
//            String account = userMap.get("account").toString();
//            String email = userMap.get("email").toString();
//            Object cardObj = userMap.get("card");
//            Object nameObj = userMap.get("name");
//            au = new AuthzParam();
//            au.setId(auId);
//            au.setAccount(account);
//            au.setEmail(email);
//            if(cardObj != null){
//                au.setCard((String) cardObj);
//            }
//            if(nameObj != null){
//                au.setName((String) nameObj);
//            }
//        }
//
//        return au;
//    }
}