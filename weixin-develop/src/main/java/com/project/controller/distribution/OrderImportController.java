package com.project.controller.distribution;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;
import com.project.common.util.LogUtil;
import com.project.service.order.OrderService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by goforit on 2017/12/24.
 */
@Controller
@RequestMapping("/distribution/orderImport")
public class OrderImportController {
    private Log logger = LogUtil.getLogger(getClass());

    @Autowired
    private OrderService orderService;

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

    @RequestMapping(method = RequestMethod.GET,value = "readDetail")
    public @ResponseBody
    Result readDetail() {
        try {
            orderService.readDetail();
            return ResultBuilder.getInstance().build(ExceptionEnum.ORDER_IMPORT_LOGIN,"");
        } catch (BusinessException e) {
            return e.getResult();
        }
    }
}
