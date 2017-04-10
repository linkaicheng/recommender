package com.cheng.mall.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author linkaicheng 首页控制器
 *
 */
@Controller
public class HomeController {
	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String main() {
		return "index";
	}

	/**
	 * 加载菜单栏
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String loadMenu() {
		return "menu";
	}

	/**
	 * 加载底部栏
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/bottom" }, method = RequestMethod.GET)
	public String loadBottom() {
		return "bottom";
	}

	/**
	 * 进入登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/toLogin" }, method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	/**
	 * 后台登录
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午3:21:56
	 * @return
	 *
	 */
	@RequestMapping(value = { "/toAdmin" }, method = RequestMethod.GET)
	public String toAdminLogin() {
		return "/admin/adminLogin";
	}

	/**
	 * 进入注册页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/toRegister" }, method = RequestMethod.GET)
	public String toRegister() {
		return "register";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}
}
