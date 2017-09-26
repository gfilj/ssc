package com.project.controller;

import com.project.spider.model.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	
	@RequestMapping(method = RequestMethod.GET,value="/mkg/api/load-issue/{lotteryId}")
    public @ResponseBody Row loadIssue(@PathVariable("lotteryId") String gameId, @RequestParam(value = "_", required = false) String currenttime) {
        return sscIssueService.loadIssue(Integer.parseInt(gameId));
    }
	
}
