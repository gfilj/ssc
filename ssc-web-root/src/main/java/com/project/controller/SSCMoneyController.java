package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.api.ResponseVO;
import com.project.service.LoadTimeService;
import com.project.service.UserMonetaryInfoService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCMoneyController {
	
	@Autowired
	private UserMonetaryInfoService userMonetaryInfoService;
	
	@RequestMapping(method = RequestMethod.GET,value="/mkg/api/users/user-monetary-info")
    public @ResponseBody ResponseVO userMonetaryInfo(@RequestParam(value = "_", required = false) String _) {
        return userMonetaryInfoService.userMonetaryInfo();
    }
	
}
