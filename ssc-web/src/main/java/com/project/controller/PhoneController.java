package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.manager.HuafeiduoManager;


@Controller
@RequestMapping("/phone")
public class PhoneController {

	@Autowired
	private HuafeiduoManager huafeiduoManager;

	/**
	 * 是否可充值检查
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/orderCheck", method=RequestMethod.GET)
	@ResponseBody
	public String orderPhoneCheck(HttpServletRequest request, HttpServletResponse response) {
		String phoneNumber = request.getParameter("phone_number");// 充值电话号码
		String cardWorth = request.getParameter("card_worth");// 充值金额
		
		return huafeiduoManager.orderPhoneCheck(phoneNumber, cardWorth);
	}
	/**
	 * 话费充值
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/recharge", method=RequestMethod.GET)
	@ResponseBody
	public void recharge(HttpServletRequest request, HttpServletResponse response) {
		
		String phoneNumber = request.getParameter("phone_number");// 充值电话号码
		String cardWorth = request.getParameter("card_worth");// 充值金额
		String spOrderId = request.getParameter("order_id");// 平台订单号，唯一
		huafeiduoManager.orderPhoneSubmit(phoneNumber, cardWorth, spOrderId);
	}
	
}
