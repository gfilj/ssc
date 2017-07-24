package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.api.BaseVO;
import com.project.model.api.ResponseVO;
import com.project.service.LoadTimeService;
import com.project.service.SSCLotteryService;
import com.project.service.UserMonetaryInfoService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCLotteryController {
	
	@Autowired
	private SSCLotteryService sSCLotteryService;
	
	@RequestMapping(method = RequestMethod.GET,value="/mkg/api/lottery/onlineLotterys")
    public @ResponseBody BaseVO loadTime(@RequestParam(value = "t", required = false) String t, @RequestParam(value = "_", required = false) String _) {
        return sSCLotteryService.onlineLotterys();
    }
	
	
}
