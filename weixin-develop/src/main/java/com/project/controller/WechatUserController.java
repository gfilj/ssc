package com.project.controller;

import com.project.common.exception.BusinessException;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.service.user.WechatUserService;
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
    /*

     */
    @RequestMapping("/list")
    public String list(Page page, Model model) {
        try {
            List<User> list = wechatUserService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "userlist";
    }


}
