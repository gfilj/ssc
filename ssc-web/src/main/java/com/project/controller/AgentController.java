package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.manager.AccountManager;
import com.project.util.CommonUtil;
import com.project.util.HttpServletUtil;
import com.project.util.Md5;

/**
 * 代理商
 * 
 * CREATE TABLE `url` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `short_url` varchar(255) DEFAULT NULL COMMENT '短链接',
  `long_url` varchar(255) DEFAULT NULL COMMENT '长链接',
  `account_id` int(11) DEFAULT NULL COMMENT '创建用户ID',
  `create_time` int(10) DEFAULT NULL,
  `update_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Controller
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AccountManager accountManager;

	/**
	 * 代理创建帐号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/create/{accountId}", method = RequestMethod.POST)
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String userType = request.getParameter("userType");// 用户类型
		String name = request.getParameter("username");// 用户名
		String password = request.getParameter("password");// 密码
		String point = request.getParameter("point");// 返点

		int result = accountManager.createAgent(name, Md5.crypt(password), point, userType, accountId);
		
		return HttpServletUtil.getResponseJsonData(result, name, "success");
	}

	/**
	 * 通过短链接开户
	 * @param request
	 * @param response
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="/create_link/{accountId}", method=RequestMethod.POST)
	public String createLink(HttpServletRequest request, HttpServletResponse response,
						 @PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String url = "register.html?";
		String shortUrl = CommonUtil.shortUrl(url);

		accountManager.createLink(shortUrl, shortUrl, accountId);

		return HttpServletUtil.getResponseJsonData(1, "success");
	}

	/**
	 * 通过短链接匹配得到长连接
	 * @param request
	 * @param response
	 * @param shortUrl
	 * @return
	 */
	@RequestMapping(value="/register/{shortUrl}", method=RequestMethod.GET)
	public String redirectUrl(HttpServletRequest request, HttpServletResponse response,
							 @PathVariable("shortUrl") String shortUrl) {
		HttpServletUtil.initResponse(response);

		String url = accountManager.getlongUrl(shortUrl);// 得到短链接的长链接地址
//		String url = "login.html";
		return "redirect:/" + url;
//		return HttpServletUtil.getResponseJsonData(1, url, "success");
	}

	/**
	 * 修改代理上下用户信息
	 * @param request
	 * @param response
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value="/change/{accountId}", method=RequestMethod.POST)
	public String change(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("accountId") String accountId) {
		HttpServletUtil.initResponse(response);
		String payeeName = accountManager.getPayeeName(accountId);
		String name = "";
		if (payeeName != null && "".equals(payeeName)) {
			name = payeeName.split("")[1] + "**";
		}
		
		return HttpServletUtil.getResponseJsonData(1, name, "success");
	}
}
