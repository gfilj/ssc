package com.project.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.service.weixin.user.WechatUserReleationLogService;
import com.project.service.weixin.user.WechatUserReleationService;
import com.project.service.weixin.user.WechatUserService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @Autowired
    private WechatUserReleationLogService wechatUserReleationLogService;

    Log logger = LogUtil.getLogger(getClass());

    @RequestMapping("/list")
    public String list(Page page, Model model) {
        try {
            //设置Url
//            page.setUrl("/wechat/user/list");
            PageInfo<User> list = wechatUserService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "userlist";
    }

    @RequestMapping("/search")
    @ResponseBody
    public Result search(String openid) {
        try {
            //设置Url
//            page.setUrl("/wechat/user/listjson");
            User user = wechatUserService.search(openid);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USER, user);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }
    }

    @RequestMapping("/releation")
    public String releation(Page page, Model model) {
        try {
            //设置Url
//            page.setUrl("/wechat/user/releation");
            //List<UserRelation> list = wechatUserReleationService.list(page);
            PageInfo<UserRelation> list = wechatUserReleationService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "releationlist";
    }

    @RequestMapping(value = "/listjson")
    @ResponseBody
    public Result listjson(Page page) {
        try {
            //设置Url
//            page.setUrl("/wechat/user/listjson");
            PageInfo<User> pageInfo = wechatUserService.list(page);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USER_LIST, pageInfo);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }

    }

    @RequestMapping(value = "/releationjson")
    @ResponseBody
    public Result releationjson(Page page) {
        try {
            //设置Url
//            page.setUrl("/wechat/user/releationjson");
            PageInfo<UserRelation> pageInfo = wechatUserReleationService.list(page);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERRELEATION_LIST, pageInfo);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }

    }

    @RequestMapping(value = "/releationlogjson")
    @ResponseBody
    public Result releationlogjson(Page page) {
        try {
            //设置Url
//            page.setUrl("/wechat/user/releationjson");
            PageInfo<UserRelation> pageInfo = wechatUserReleationLogService.list(page);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERRELEATION_LIST, pageInfo);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }

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
