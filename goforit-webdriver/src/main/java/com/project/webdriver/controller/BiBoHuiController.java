//package com.project.webdriver.controller;
//
//import com.project.webdriver.model.BiBoHuiMitiBean;
//import com.project.webdriver.model.Result;
//import com.project.webdriver.service.UserService;
//import com.project.webdriver.service.muti.BiBoHuiMutiGrab;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by goforit on 2017/9/4.
// */
//
//@Controller
//@RequestMapping("/bibohui")
//public class BiBoHuiController {
//
//    @Autowired
//    private BiBoHuiMutiGrab biBoHuiMutiGrab;
//
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(method = RequestMethod.POST, value = "/start")
//    public @ResponseBody
//    Result start(@RequestBody BiBoHuiMitiBean biBoHuiMitiBean) {
//        if(userService.isValid(biBoHuiMitiBean.getUserName())){
//            biBoHuiMutiGrab.start(biBoHuiMitiBean);
//            return new Result("启动成功!");
//        }
//        return new Result("请开通权限!");
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/stop")
//    public @ResponseBody Result stop(@RequestBody BiBoHuiMitiBean biBoHuiMitiBean) {
//        biBoHuiMutiGrab.stop(biBoHuiMitiBean);
//        return new Result("关闭成功！");
//
//    }
//
//}
