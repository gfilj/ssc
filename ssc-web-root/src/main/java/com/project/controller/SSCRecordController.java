package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.api.BaseVO;
import com.project.model.api.ResponseVO;
import com.project.service.SSCRecordService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCRecordController {
	
	@Autowired
	private SSCRecordService sSCRecordService;
	
	@RequestMapping(method = RequestMethod.GET,value="/mkg/api/query/orders")
    public @ResponseBody ResponseVO queryOrders(@RequestParam(value = "_", required = false) String _) {
        return sSCRecordService.queryOrders();
    }
	
	
	@RequestMapping(method = RequestMethod.POST,value="/mkg/api/order/cancle")
    public @ResponseBody BaseVO orderCancle(@RequestParam(value = "orderId", required = true) String orderId) {
        return sSCRecordService.orderCancle();
    }
	
}
