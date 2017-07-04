package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.util.HttpServletUtil;


/**
 * 报表管理
 *
 */
@Controller
@RequestMapping("/report")
public class ReportController {

	private static Logger LOG = LogManager.getLogger(ReportController.class);
	
	/**
	 * 查看个人报表
	 * @param request
	 * @param response
	 * @param accountName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/personal", method=RequestMethod.GET)
	@ResponseBody
	public String report(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String accountId, @PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		HttpServletUtil.initResponse(response);

		
		return null;
	}

	/**
	 * 查看团队报表
	 * @param request
	 * @param response
	 * @param accountName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/group", method = RequestMethod.GET)
	@ResponseBody
	public String groupReport(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String accountId, @PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		
		
		return null;
	}
	
}
