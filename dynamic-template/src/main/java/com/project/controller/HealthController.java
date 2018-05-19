package com.project.controller;

import com.project.common.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by goforit on 2018/5/6.
 */
@Controller
@RequestMapping("/health")
public class HealthController {
    Log logger = LogUtil.getLogger(getClass());

    /**
     * return sever current status
     * （用于nginx探活)
     */
    @RequestMapping("/status")
    public @ResponseBody String getServerStatus() {
        return "dynamic-template: Hello! I'm alive! ";
    }

}
