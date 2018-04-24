package com.project.controller.template;

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
@RequestMapping("/template")
public class TemplateController {
    private Log logger = LogUtil.getLogger(getClass());

    @Autowired
    private LoginService loginService;

    @RequestMapping("/design")
    public String design() {
        logger.info(LogUtil.logstr("模板设计","路径","design"));
        return "templateList";
    }

}
