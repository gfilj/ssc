package com.project.controller.wechat;

import com.github.pagehelper.PageInfo;
import com.project.common.exception.ArgumentException;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.ArgumentsUtil;
import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.model.vo.UserSearchVO;
import com.project.service.weixin.access.WechatAccessService;
import com.project.service.weixin.user.WechatUserReleationLogService;
import com.project.service.weixin.user.WechatUserReleationService;
import com.project.service.weixin.user.WechatUserService;
import com.project.webmagic.model.OrderDetailDB;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.RedisDao;
import redis.factory.RedisDaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    protected WechatAccessService wechatAccessService;

    private RedisDao sessionRedisDao = RedisDaoFactory.getSimpleRedisDao("session");

    Log logger = LogUtil.getLogger(getClass());

    @RequestMapping("/listpage")
    public String list(Page page, Model model) {
        try {
            PageInfo<User> list = wechatUserService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "userlist";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result search(String openid, HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = wechatUserService.get(openid);
            if(user.getQrCode()==null){
                user.setQrCode(wechatAccessService.generateShowRRUrl(user.getOpenid()));
                wechatUserService.update(user);
            }

            return ResultBuilder.build(ExceptionEnum.WECHAT_USER, user);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }
    }

    @RequestMapping("/releation")
    public String releation(Page page, Model model) {
        try {

            PageInfo<UserRelation> list = wechatUserReleationService.list(page);
            model.addAttribute("page", page);
            model.addAttribute("list", list);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "releationlist";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Result listjson(Page page) {
        try {

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
            PageInfo<UserRelation> pageInfo = wechatUserReleationLogService.list(page);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERLOG_LIST, pageInfo);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }

    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public Result serach(UserSearchVO searchVO) {
        try {
            PageInfo<User> pageInfo = wechatUserService.search(searchVO);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERLOG_LIST, pageInfo);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }
    }

    @RequestMapping(value = "/merge", produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public Result merge(@RequestBody UserSearchVO searchVO) {
        try {
            logger.info(searchVO);
            String pageInfo = wechatUserService.markDelete(searchVO.getUserList());
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERLOG_LIST, pageInfo);
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

    /**
     * 更新微信用户信息，包括将京东手机号绑定到微信上
     * @param user
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(User user) {
        try {
            wechatUserService.update(user);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERLOG_LIST, "success");
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }
    }

    /**
     * 更新微信用户信息，包括将京东手机号绑定到微信上
     * @param openid
     * @return
     */
    @RequestMapping(value = "/getOrderByOpenid")
    @ResponseBody
    public Result getOrderByOpenid(String openid) {
        try {
            ArgumentsUtil.checkArgument(openid == null||openid.isEmpty(),
                    ExceptionEnum.ARGUMENT_ILLEGAL_CAUSE, null);
            PageInfo<OrderDetailDB>orders = wechatUserService.getOrderByOpenid(openid);
            return ResultBuilder.build(ExceptionEnum.WECHAT_USERLOG_LIST, orders);
        } catch (BusinessException|ArgumentException e) {
            logger.error(e.getMessage(), e);
            return e.getResult();
        }
    }

}
