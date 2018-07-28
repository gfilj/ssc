package com.project.controller.login;

import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.CookieUtil;
import com.project.common.util.LogUtil;
import com.project.constant.WebConstant;
import com.project.model.sql.SystemUser;
import com.project.model.vo.SystemUserVo;
import com.project.service.login.LoginService;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import share.WebContext;
import share.WebSession;

import javax.annotation.Resource;
import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Fenix_Bao on 2018/3/31.
 */
@Controller
@RequestMapping("/system")
public class LoginController {
    //用户信息数据有效时间
    public static final int USER_CACHE_TIME_OUT_SECONDS = 12 * 60 * 60;

    private Log logger = LogUtil.getLogger(getClass());

    @Resource
    private LoginService loginService;

    @RequestMapping(value="/login")
    public @ResponseBody Result login(SystemUserVo systemUserVo) {
        SystemUser systemUser = null;
        logger.info("try to login:"+systemUserVo);
        try {
            systemUser = loginService.login(systemUserVo.getUsername());
        } catch (Exception e) {
            logger.error("认证出错！" + e);
            return ResultBuilder.build(ExceptionEnum.SYS_USER_LOGIN_FAIL,systemUserVo.getUsername());
        }
        if(systemUser!=null&&systemUser.getPassword().equals(systemUserVo.getPassword())){
            WebSession session = WebContext.getSession().initWebSession();
            session.setAttribute(WebConstant.CURRENT_USER, systemUser);
            session.setMaxInactiveInterval(USER_CACHE_TIME_OUT_SECONDS);
            return ResultBuilder.build(ExceptionEnum.SYS_USER_LOGIN_SUCCESS,systemUserVo.getUsername());
        }
        return ResultBuilder.build(ExceptionEnum.SYS_USER_LOGIN_FAIL,systemUserVo.getUsername());
    }


    @RequestMapping(value="/currentuser")
    public @ResponseBody Result currentuser() {
        WebSession session = WebContext.getSession().initWebSession();
        SystemUser systemUser = session.getAttribute(WebConstant.CURRENT_USER, SystemUser.class);
        return ResultBuilder.build(ExceptionEnum.SYS_USER_CURRENT_SUCCESS,systemUser.getUsername());
    }


    @RequestMapping(value="/quit")
    @ResponseBody
    public WebResult quit(HttpServletRequest request, HttpServletResponse response) {
        WebResult webResult = null;
        Map<String,Object> map = new HashMap<String,Object>();

//
//            CookieUtil.deleteCookie(request, response, ProjectConfig.cookieDomain);
//
            WebSession session = WebContext.getSession();
            session.removeSessionInCookie();
            map.put("status", true);
            map.put("loginUrl", "http://localhost:3006/#/login");
//
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