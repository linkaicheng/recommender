package com.cheng.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户登录注册
 * 
 * @author linkaicheng
 *
 */
@Controller
public class UserController {
	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String register() {
		return "login";
	}

}
