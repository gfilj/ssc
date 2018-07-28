package com.project.controller.distribution;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.LogUtil;
import com.project.model.vo.OrderSearchVO;
import com.project.model.vo.Page;
import com.project.service.order.OrderService;
import com.project.webdriver.login.LoginService;
import com.project.webdriver.login.model.LoginModel;
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
@RequestMapping("/distribution/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private Log logger = LogUtil.getLogger(getClass());

    @Autowired
    private LoginService loginService;
    @RequestMapping(value = "qrcode")
    @ResponseBody
    public Result login() {
        try {
            LoginModel login = loginService.login();
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN, login);
        } catch (BusinessException e) {
            return e.getResult();
        }
    }

    @RequestMapping(value = "result")
    @ResponseBody
    public Result result() {
        boolean success = loginService.isSuccess();
        return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN, success);
    }

    @RequestMapping(method = RequestMethod.GET,value = "read")
    public @ResponseBody
    Result read() {
        try {
            orderService.read();
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN,"");
        } catch (BusinessException e) {
            return e.getResult();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "entercode")
    public @ResponseBody
    Result enterCode(String code) {
        try {
            loginService.enterCode(code);
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN, "");
        } catch (BusinessException e) {
            return e.getResult();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "page")
    String page(Model model) {
        model.addAttribute("urlContent", loginService.currentSource());
        return "show";
    }

    @RequestMapping(method = RequestMethod.GET,value = "list")
    public @ResponseBody Result list(Page page) {
        try {
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_LIST, orderService.list(page));
        } catch (BusinessException e) {
            return e.getResult();
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = "search")
    public @ResponseBody Result search(OrderSearchVO searchVO) {
        try {
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_LIST, orderService.search(searchVO));
        } catch (BusinessException e) {
            return e.getResult();
        }
    }
}
