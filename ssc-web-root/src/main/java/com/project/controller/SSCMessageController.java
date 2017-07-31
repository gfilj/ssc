package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.api.ResponseVO;
import com.project.service.SSCMessageService;
import com.project.service.UserMonetaryInfoService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCMessageController {
	
	@Autowired
	private SSCMessageService sSCMessageService;
	@RequestMapping(method = RequestMethod.GET,value="u/api/message/queryUnRMCount")
    public @ResponseBody ResponseVO queryUnRMCount(@RequestParam(value = "_", required = false) String _) {
        return sSCMessageService.queryUnRMCount();
    }
	
	

}
