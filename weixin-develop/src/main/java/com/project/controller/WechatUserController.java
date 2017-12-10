package com.project.controller;

import com.project.common.exception.BusinessException;
import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.service.weixin.user.WechatUserReleationService;
import com.project.service.weixin.user.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by goforit on 2017/11/27.
 */
@Controller
@RequestMapping("/wechat/user")
public class WechatUserController {

    @Autowired
    private WechatUserService wechatUserService;
    @Autowired
    private WechatUserReleationService wechatUserReleationService;
    /**
     * 用户列表
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Page page, Model model) {
        try {
            //设置Url
            page.setUrl("/wechat/user/list");
            List<User> list = wechatUserService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "userlist";
    }

    @RequestMapping("/releation")
    public String releation(Page page, Model model) {
        try {
            //设置Url
            page.setUrl("/wechat/user/releation");
            List<UserRelation> list = wechatUserReleationService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "releationlist";
    }

//    @RequestMapping("/log")
//    public String releation(Page page, Model model) {
//        try {
//            //设置Url
//            page.setUrl("/wechat/user/releation");
//            List<UserRelation> list = wechatUserReleationService.list(page);
//            model.addAttribute("page", page);
//            model.addAttribute("list", list);
//        } catch (BusinessException e) {
//            e.printStackTrace();
//        }
//        return "loglist";
//    }

}
