package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.api.ResponseVO;
import com.project.service.SSCIssueService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCIssueController {
	
	@Autowired
	private SSCIssueService sscIssueService;
	
	@RequestMapping(method = RequestMethod.GET,value="/mkg/api/load-time")
    public @ResponseBody ResponseVO loadTime(@RequestParam(value = "id", required = false) double id, @RequestParam(value = "gameId", required = false) int gameId) {
        return sscIssueService.loadtime(gameId);
    }
	
	@RequestMapping(method = RequestMethod.GET,value="/mkg/api/load-issue/1")
    public @ResponseBody ResponseVO loadIssue(@RequestParam(value = "_", required = false) String _) {
//        return sscIssueService.loadtime();
		return null;
    }
	
}
