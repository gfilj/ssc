package com.project.controller;

import com.project.common.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by goforit on 2018/5/6.
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
    Log logger = LogUtil.getLogger(getClass());

    /**
     * return sever current status
     * （用于nginx探活)
     */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message","hello");
        return "index";
    }
    @RequestMapping("/getEditPage")
    public String getEditPage(){
        return "pageEdit";
    }

}
