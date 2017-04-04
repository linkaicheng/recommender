package com.cheng.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author linkaicheng
 *
 */
@Controller
public class HomeController {
	/**
	 * @return
	 */
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String main() {
		return "index";
	}

	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	public String loadMenu() {
		return "menu";
	}

	@RequestMapping(value = { "/bottom" }, method = RequestMethod.GET)
	public String loadBottom() {
		return "bottom";
	}
}
