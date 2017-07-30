package com.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.api.ResponseVO;
import com.project.service.SSCBetsService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCBetsController {
	
	@Autowired
	private SSCBetsService sSCBetsService;
	
	@RequestMapping(method = RequestMethod.POST,value="mkg/api/bets/bet")
    public @ResponseBody ResponseVO bets(@RequestParam Map<String, Object> param) {
		System.out.println(param);
		sSCBetsService.insertSelective(param);
        return sSCBetsService.betsResult();
    }
	
}
