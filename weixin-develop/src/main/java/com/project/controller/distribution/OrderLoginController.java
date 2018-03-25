package com.project.controller.distribution;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.LogUtil;
import com.project.webdriver.login.LoginService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by goforit on 2017/12/23.
 */
@Controller
@RequestMapping("/distribution/orderLogin")
public class OrderLoginController {
    private Log logger = LogUtil.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET,value = "login")
    public @ResponseBody
    Result login() {
        try {
            loginService.login();
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN,"");
        } catch (BusinessException e) {
            return e.getResult();
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "entercode")
    public @ResponseBody
    Result enterCode(String code) {
        try {
            loginService.enterCode(code);
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN,"");
        } catch (BusinessException e) {
            return e.getResult();
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = "page")
    String page(Model model){
        model.addAttribute("urlContent",loginService.currentSource());
        return "show";
    }
}
