package com.cheng.mall.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cheng.mall.bean.AdminUser;
import com.cheng.mall.service.admin.AdminUserService;

/**
 * 
 * @author linkaicheng
 * @date 2017年4月8日 下午2:52:15 后台管理
 *
 */
@Controller
public class AdminHomeController {
	@Resource
	private AdminUserService adminUserService;

	/**
	 * 登录 进入首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/adminLogin" }, method = RequestMethod.POST)
	public String adminLogin(String userName, String password, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		AdminUser adminUser = adminUserService.login(userName, password);
		if (adminUser != null) {
			session.setAttribute("adminUser", adminUser);
			return "/admin/product";
		}
		return "error";
	}

	/**
	 * 登录 进入首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/adminLogout" }, method = RequestMethod.GET)
	public String adminLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/admin/adminLogin";
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午2:56:57
	 * @return 进入一级分类管理页面
	 *
	 */
	@RequestMapping(value = { "/toCategory" }, method = RequestMethod.GET)
	public String toCategory() {
		return "/admin/category";
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午2:56:57
	 * @return 进入二级分类管理页面
	 *
	 */
	@RequestMapping(value = { "/toCategorysecond" }, method = RequestMethod.GET)
	public String toCategorysecond() {
		return "/admin/categorysecond";
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午2:56:57
	 * @return 进入订单管理页面
	 *
	 */
	@RequestMapping(value = { "/toOrder" }, method = RequestMethod.GET)
	public String toOrder() {
		return "/admin/order";
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午2:56:57
	 * @return 进入商品管理页面
	 *
	 */
	@RequestMapping(value = { "/toProduct" }, method = RequestMethod.GET)
	public String toProduct() {
		return "/admin/product";
	}

	@RequestMapping(value = { "/toRecommend" }, method = RequestMethod.GET)
	public String toRecommend() {
		return "/admin/recommender";
	}

}
